package bits.wilp.FullStackDevelopment.service;


import bits.wilp.FullStackDevelopment.entity.VaccinationDrive;
import bits.wilp.FullStackDevelopment.repository.VaccinationDriveRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class VaccinationDriveService {

    private final VaccinationDriveRepository driveRepository;

    public VaccinationDrive createDrive(VaccinationDrive drive) {
        LocalDate today = LocalDate.now();

        if (drive.getDriveDate().isBefore(today.plusDays(15))) {
            throw new IllegalArgumentException("Vaccination drives must be scheduled at least 15 days in advance.");
        }

        for (String grade : drive.getApplicableGrades()) {
            if (!driveRepository.findByDriveDateAndGrade(grade, drive.getDriveDate()).isEmpty()) {
                throw new IllegalStateException("A drive is already scheduled for grade " + grade + " on " + drive.getDriveDate());
            }
        }

        drive.setCompleted(false);
        return driveRepository.save(drive);
    }

    public VaccinationDrive updateDrive(Long id, VaccinationDrive updated) {
        VaccinationDrive existing = driveRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Drive not found with ID " + id));

        if (existing.getDriveDate().isBefore(LocalDate.now())) {
            throw new IllegalStateException("Cannot edit a past/completed drive.");
        }

        existing.setDriveDate(updated.getDriveDate());
        existing.setVaccineName(updated.getVaccineName());
        existing.setNumberOfVaccinesAvailable(updated.getNumberOfVaccinesAvailable());
        existing.setApplicableGrades(updated.getApplicableGrades());

        return driveRepository.save(existing);
    }

    public List<VaccinationDrive> listDrives() {
        List<VaccinationDrive> drives = driveRepository.findAllByOrderByDriveDateAsc();

        for (VaccinationDrive drive : drives) {
            if (!drive.isCompleted() && drive.getDriveDate().isBefore(LocalDate.now())) {
                drive.setCompleted(true);
                driveRepository.save(drive);
            }
        }

        return drives;
    }

    public void deleteDrive(Long id) {
        driveRepository.deleteById(id);
    }

    public VaccinationDrive getDrive(Long id) {
        return driveRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Drive not found with ID " + id));
    }

    public List<VaccinationDrive> getAllDrives() {
        return driveRepository.findAll();
    }
}

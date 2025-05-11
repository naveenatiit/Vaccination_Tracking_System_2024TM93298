package bits.wilp.FullStackDevelopment.service;


import bits.wilp.FullStackDevelopment.dto.VaccinationMarkRequest;
import bits.wilp.FullStackDevelopment.entity.Student;
import bits.wilp.FullStackDevelopment.entity.Vaccination;
import bits.wilp.FullStackDevelopment.entity.VaccinationDrive;
import bits.wilp.FullStackDevelopment.repository.StudentRepository;
import bits.wilp.FullStackDevelopment.repository.VaccinationDriveRepository;
import bits.wilp.FullStackDevelopment.repository.VaccinationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class VaccinationService {

    @Autowired
    private final VaccinationRepository vaccinationRepository;
    @Autowired
    private final StudentRepository studentRepository;
    @Autowired
    private final VaccinationDriveRepository driveRepository;

    public void markVaccinated(VaccinationMarkRequest request) {
        boolean alreadyVaccinated = vaccinationRepository
                .existsByStudentIdAndVaccineName(request.getStudentId(), request.getVaccineName());

        if (alreadyVaccinated) {
            throw new IllegalStateException("Student already vaccinated for this vaccine");
        }

        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new NoSuchElementException("Student not found"));
        VaccinationDrive drive = driveRepository.findById(request.getDriveId())
                .orElseThrow(() -> new NoSuchElementException("Drive not found"));

        Vaccination vaccination = Vaccination.builder()
                .student(student)
                .vaccineName(request.getVaccineName())
                .vaccinationDate(request.getVaccinationDate())
                .drive(drive)
                .build();

        vaccinationRepository.save(vaccination);
    }
}


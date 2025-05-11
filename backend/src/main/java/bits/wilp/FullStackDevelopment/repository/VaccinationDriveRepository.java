package bits.wilp.FullStackDevelopment.repository;

import bits.wilp.FullStackDevelopment.entity.VaccinationDrive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface VaccinationDriveRepository extends JpaRepository<VaccinationDrive, Long> {

    @Query("SELECT d FROM VaccinationDrive d WHERE d.driveDate = :driveDate AND :grade IN elements(d.applicableGrades)")
    List<VaccinationDrive> findByDriveDateAndGrade(String grade, LocalDate driveDate);

    List<VaccinationDrive> findAllByOrderByDriveDateAsc();

    @Query(value = "SELECT * FROM vaccination_drives WHERE drive_date BETWEEN CURRENT_DATE AND CURRENT_DATE + INTERVAL '30 days'", nativeQuery = true)
    List<VaccinationDrive> findUpcomingDrivesWithin30Days();
}

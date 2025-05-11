package bits.wilp.FullStackDevelopment.repository;

import bits.wilp.FullStackDevelopment.entity.Vaccination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccinationRepository extends JpaRepository<Vaccination, Long> {
    boolean existsByStudentIdAndVaccineName(Long studentId, String vaccineName);
}

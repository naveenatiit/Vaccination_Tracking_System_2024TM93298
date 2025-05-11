package bits.wilp.FullStackDevelopment.repository;

import bits.wilp.FullStackDevelopment.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByNameContainingIgnoreCase(String name);

    List<Student> findByGrade(String grade);

    @Query("SELECT s FROM Student s JOIN s.vaccination v WHERE v.vaccineName = :vaccineName")
    List<Student> findByVaccinationName(@Param("vaccineName") String vaccineName);

    @Query("SELECT COUNT(s) FROM Student s")
    long countTotalStudents();

    @Query("SELECT COUNT(DISTINCT s) FROM Student s JOIN s.vaccination v")
    long countVaccinatedStudents();

}


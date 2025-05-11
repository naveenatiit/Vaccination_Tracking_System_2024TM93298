package bits.wilp.FullStackDevelopment.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "vaccination_drives")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VaccinationDrive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "drive_date", nullable = false)
    private LocalDate driveDate;

    @Column(name = "number_of_vaccines_available", nullable = false)
    private int numberOfVaccinesAvailable;

    @Column(name = "completed")
    private boolean completed = false;

    @Column(name = "vaccine_name", nullable = false)
    private String vaccineName;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "drive_applicable_grades", joinColumns = @JoinColumn(name = "drive_id"))
    @Column(name = "grade", nullable = false)
    private List<String> applicableGrades;
}


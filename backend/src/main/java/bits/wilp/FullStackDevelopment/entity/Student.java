package bits.wilp.FullStackDevelopment.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "students")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String grade;
    private String parentContact;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Vaccination> vaccination;
}


package bits.wilp.FullStackDevelopment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class UpcomingDriveDTO {
    private Long id;
    private String vaccineName;
    private LocalDate driveDate;
    private List<String> applicableGrades;
}
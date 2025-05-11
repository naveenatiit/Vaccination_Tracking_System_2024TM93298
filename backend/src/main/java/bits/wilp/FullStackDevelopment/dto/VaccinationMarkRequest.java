package bits.wilp.FullStackDevelopment.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class VaccinationMarkRequest {
    private Long studentId;
    private Long driveId;
    private String vaccineName;
    private LocalDate vaccinationDate;
}

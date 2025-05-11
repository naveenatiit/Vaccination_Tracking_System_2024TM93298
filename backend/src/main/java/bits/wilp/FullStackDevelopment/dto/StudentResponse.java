package bits.wilp.FullStackDevelopment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentResponse {
    private Long id;
    private String name;
    private String grade;
    private String parentContact;
}

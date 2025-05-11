package bits.wilp.FullStackDevelopment.dto;

import lombok.Data;

@Data
public class StudentRequest {
    private Long id;
    private String name;
    private String grade;
    private String parentContact;
}

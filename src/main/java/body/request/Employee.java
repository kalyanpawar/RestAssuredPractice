package body.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employee {
    private String firstName;
    private String lastName;
    private Integer age;
    private Double salary;
    private String dateOfJoining;
    private String dob;
    private List<Email> emails;
}

package com.example.jobseekingsystem.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Check(constraints = "role = 'JOB_SEEKER' or role = 'EMPLOYER' ")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "User email cannot be null")
    @Size(min = 10 , max = 50 , message = "User email must be between 10 and 50")
    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email RegEx rules:"+
            "- Format: Must follow the pattern `local-part@domain`.\n" +
            "- Local part can contain:\n" +
            "  - Letters (A-Z, a-z)\n" +
            "  - Numbers (0-9)\n" +
            "  - Special characters (`!#$%&'*+/=?^_`{|}~-`)\n" +
            "  - Periods (.) but not at the start or end, and not consecutively.\n" +
            "- Domain part must contain:\n" +
            "  - Letters (A-Z, a-z)\n" +
            "  - Numbers (0-9)\n" +
            "  - Periods (.) to separate subdomains.\n" +
            "  - Must end with a valid top-level domain (e.g., `.com`, `.org`, `.net`).")

    @Column(columnDefinition = "varchar(50) unique not null")
    private String email;


    @NotEmpty(message = "user name cannot be null")
    @Size(min = 5, max = 49, message = "user name must be between 5 and 50 characters")
    @Pattern(regexp = "^[A-Za-z][A-Za-z0-9 _-]{5,49}$", message = "user name RegEx rules:" +
            "- Length: between 5 to 50 characters.\n" +
            "- No leading or trailing spaces.\n" +
            "- Must start with a letter.")
    @Column(columnDefinition = "varchar(50) not null")
    private String name;

    @NotEmpty(message = "User password cannot be null")
    @Size(min = 8 , max = 20 , message = "User password must be between 8 and 30")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{8,20}$", message = "password RegEx rules: \n" +
            "- Length: between 8 to 20 characters.\n" +
            "- Must contain at least:\n" +
            "   - One uppercase letter (A-Z)\n" +
            "   - One lowercase letter (a-z)\n" +
            "   - One number (0-9)\n" +
            "   - One special character (e.g., `!@#$%^&*`)\n" +
            "- No spaces allowed.")
    @Column(columnDefinition = "varchar(255) not null")
    private String password;

    @NotNull(message = "user age cannot be null")
    @Positive(message = "user age must be positive number")
    @Min(value = 21,message = "Minimum age is 21")
    private Integer age;

    @NotEmpty(message = "User role cannot be null")
    @Pattern(regexp = "^(JOB_SEEKER|EMPLOYER)" , message = "User role can be JOB_SEEKER or EMPLOYER only")
    @Column(columnDefinition = "varchar(10) not null")
    private String role;

}

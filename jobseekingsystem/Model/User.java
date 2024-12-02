package org.example.jobseekingsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Check(constraints = "role= 'JOB_SEEKER' or role= 'EMPLOYER'")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name can not be empty!")
    @Size(min= 5, message = "Name must be more than 4 letters!")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Name must contain only letters(no numbers)!")
    @Column(columnDefinition = "varchar(15) not null")
    private String name;

    @Email(message = "Email must be at a valid format!")
    @Column(columnDefinition = "varchar(20) unique")
    private String email;

    @NotEmpty(message = "Password can not be empty!")
    @Column(columnDefinition = "varchar(30) not null")
    private String password;

    @Min(22)
    @Column(columnDefinition = "int not null")
    private Integer age;

    @NotEmpty(message = "Role can not be empty!")
    @Pattern(regexp = "^(?i)(JOB_SEEKER|EMPLOYER)$", message = "Role must be either \"JOB_SEEKER\" or \"EMPLOYER\" only!")
    private String role;






}

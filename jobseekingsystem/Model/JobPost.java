package org.example.jobseekingsystem.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class JobPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Title can not be empty!")
    @Size(min= 5, message = "Job post's title length must be more than 4 characters!")
    @Column(columnDefinition = "varchar(30) not null")
    private String title;

    @NotEmpty(message = "Description can not be empty!")
    @Column(columnDefinition = "varchar(150) not null")
    private String description;

    @NotEmpty(message = "Location can not be empty!")
    @Column(columnDefinition = "varchar(20) not null")
    private String location;

    @Positive(message = "Salary must be a positive number only!")
    @Column(columnDefinition = "DOUBLE not null")
    private Double salary;

    @JsonFormat
    @Column(columnDefinition = "DATE")
    private Date postingDate;








}

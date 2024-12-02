package com.example.jobseekingsystem.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "int not null")
    private Integer userId;

    @Column( columnDefinition = "int not null")
    private Integer jobPostId;

    @NotNull(message = "Application status cannot be null")
    @Pattern(regexp = "^(Pending|Accepted|Rejected)$",
            message = "Application status must be 'Pending', 'Accepted', or 'Rejected'")
    @Column(columnDefinition = "VARCHAR(8) DEFAULT 'Pending' NOT NULL")
    private String applicationStatus;


    @Column(columnDefinition = "DATE DEFAULT CURRENT_DATE NOT NULL")
    private LocalDateTime appliedDate = LocalDateTime.now();

    @Size(max = 255, message = "Resume path cannot over 255 characters")
    @Column( columnDefinition = "VARCHAR(255)")
    private String resumePath;

    @Size(max = 500, message = "Cover letter cannot over 500 characters")
    @Column(columnDefinition = "TEXT")
    private String coverLetter;

}

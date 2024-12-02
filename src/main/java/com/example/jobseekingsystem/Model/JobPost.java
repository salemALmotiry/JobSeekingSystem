package com.example.jobseekingsystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class JobPost {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(columnDefinition = "int not null")
    private Integer userId;

    @NotEmpty(message = "title cannot be null")
    @Size(min = 4, max = 99, message = "title must be between 4 and 99 characters")
    @Pattern(regexp = "^[A-Z][a-zA-Z0-9 ,.'-]{1,99}$", message = "title RegEx rules:" +
            "- Length: between 4 to 99 characters.\n" +
            "-a-z: lowercase letters .\n" +
            "-A-Z: uppercase letters.\n" +
            "0-9: digits\n" +
            "space,',' and '_'")
    @Column(columnDefinition = "varchar(99) not null")
    private String title;

    @NotEmpty(message = "description cannot be null")
    @Size(min = 10, max = 255, message = "description must be between 10 and 255 characters")
    @Pattern(regexp = "^[a-z][a-zA-Z0-9 ,.'-]{10,255}$", message = "description RegEx rules:" +
            "- Length: between 10 to 255 characters.\n" +
            "-a-z: lowercase letters .\n" +
            "-A-Z: uppercase letters.\n" +
            "0-9: digits\n" +
            "space,',' and '_'")
    @Column(columnDefinition = "varchar(99) not null")
    private String description;

    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime postingDate =  LocalDateTime.now();
}

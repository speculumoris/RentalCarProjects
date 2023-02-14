package com.saferent.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tbl_cmessage")
public class ContactMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Size(min = 1,max = 50, message = "Your name '${validatedValue}' must be  between {min} and {max} chars long ")
    @NotNull(message = "please provide your name")
    @Column(length = 50,nullable = false)
    private String name;

    @Size(min = 5,max = 50, message = "Your subject '${validatedValue}' must be  between {min} and {max} chars long ")
    @NotNull(message = "please provide your subject")
    @Column(length = 50,nullable = false)
    private String subject;

    @Size(min = 20,max = 200, message = "Your body '${validatedValue}' must be  between {min} and {max} chars long ")
    @NotNull(message = "please provide your body")
    @Column(length = 50,nullable = false)
    private String body;

    @Email(message = "provide valid email")
    @Column(length = 50,nullable = false)
    private String email;

}

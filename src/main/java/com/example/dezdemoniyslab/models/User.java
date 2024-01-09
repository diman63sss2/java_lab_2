package com.example.dezdemoniyslab.models;
import jakarta.persistence.*;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder()
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(nullable = false)
    private Long id;

    @Setter(AccessLevel.NONE)
    @Column(name = "uuid", nullable = false)
    private UUID uuid;

    @Column(name = "username", nullable = false, unique = false)
    private String username;

    @Column(name = "first_name", nullable = false)
    private String firstname;

    @Column(name = "last_name", nullable = false)
    private String lastname;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @Column(name = "registration_date", nullable = false)
    private LocalDateTime registrationDate;


    @Column(name = "last_modified_date", nullable = false)
    private LocalDateTime lastModifiedDate;

    @Enumerated(EnumType.STRING)
    private Role role;



    @PrePersist
    public void initialize() {
        uuid = UUID.randomUUID();
        isDeleted = false;
        registrationDate = LocalDateTime.now();
        lastModifiedDate = registrationDate;
        role = Role.USER;
    }

    @PreUpdate
    public void update(){
        lastModifiedDate = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "user")
    private List<Book> books;

}
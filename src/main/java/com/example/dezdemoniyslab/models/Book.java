package com.example.dezdemoniyslab.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder()
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    @Column(nullable = false)
    private Long id;

    @Setter(AccessLevel.NONE)
    @Column(name = "uuid", nullable = false)
    private UUID uuid;

    @Column(name = "content")
    @Size(max = 4000)
    private String content;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;


    @Column(name = "last_modified_date", nullable = false)
    private LocalDateTime lastModifiedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    public User user;

    @PrePersist
    public void initialize() {
        uuid = UUID.randomUUID();
        isDeleted = false;
        creationDate = LocalDateTime.now();
        lastModifiedDate = creationDate;
    }

    @PreUpdate
    public void update(){
        lastModifiedDate = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", author='" + user.getId() + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

}

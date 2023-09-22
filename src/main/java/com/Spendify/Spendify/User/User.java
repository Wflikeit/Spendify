package com.Spendify.Spendify.User;

import com.Spendify.Spendify.Invoice.Invoice;
import com.Spendify.Spendify.Room.Room;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "user_email_unique", columnNames = "email")})
@Entity
public class User {
    @Id
    @SequenceGenerator(name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )

    private Long id;
    @Column(name = "name",
            length = 50,
            nullable = false
    )
    private String name;
    @Column(name = "surname",
            length = 50,
            nullable = false
    )
    private String surname;
    @Transient
    private String fullName;
    @Column(name = "password",
            length = 50,
            nullable = false
    )
    private String password;
    @Column(name = "email",
            nullable = false,
            length = 80
    )
    private String email;

    private String image;
    private Boolean isActive;

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL
    )
    private List<Invoice> invoices;
    @ManyToMany(mappedBy = "user")
    private List<Room> room;

    public User(String name, String surname, String password, String email, String image, Boolean isActive) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.image = image;
        this.isActive = isActive;
    }

    public String getFullName() {
        return name + " " + surname;
    }
}

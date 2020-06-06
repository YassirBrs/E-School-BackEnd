package com.virtualpairprogrammers.roombooking.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @NotBlank(message ="name cannot be blank.")
    private String name;
    @NotBlank(message ="surname cannot be blank.")
    private String surname;
    @NotBlank(message ="CIN cannot be blank.")
    private String CIN;
    @NotBlank(message ="register ID cannot be blank.")
    private String registreId;
    @NotBlank(message="password cannot be blank.")
    private String password;

//    @OneToMany(mappedBy = "user")
//    private List<Booking> bookings;

    public User(String name,String surname,String CIN,String registreId, String password) {
        this.name = name;
        this.surname = surname;
        this.CIN = CIN;
        this.registreId = registreId;
        this.password = password;
    }


}

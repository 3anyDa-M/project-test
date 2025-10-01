package com.project.test.tech_serv.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
@Table
public class Pasport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long  id;

    @Column(length = 100,nullable = false)
    private String firstName;
    @Column(length = 100,nullable = false)
    private String lastName;
    @Column(name = "number",nullable = false)
    private String pasportNum;
    @Column(nullable = false)
    private String address;
}

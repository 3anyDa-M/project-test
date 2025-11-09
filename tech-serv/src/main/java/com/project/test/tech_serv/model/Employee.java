package com.project.test.tech_serv.model;


import jakarta.persistence.*;
import lombok.*;


import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString

@Entity
@Table(name = "employee")
public class Employee implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 100, nullable = false)
    private String firstName;

    @Column(length = 100, nullable = false)
    private String lastName;

    @Column(length = 100, nullable = false)
    private String job;

    @Column(name = "pasport_num", length = 100)
    private String pasportNum;

    @OneToMany(mappedBy = "employee",cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    private Set<Contact> contact = new HashSet<>();

    @OneToOne(mappedBy ="employee",fetch = FetchType.LAZY)
    private Pasport pasport;
}

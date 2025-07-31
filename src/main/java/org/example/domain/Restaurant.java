package org.example.domain;

import lombok.*;
import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "restaurants")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;
    private String address;
    private String contact;
    private String description;

    @OneToOne(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private Menu menu;
}

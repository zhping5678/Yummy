package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "discount")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double standard;

    private double reduce;

}

package com.model;

import com.util.FoodType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "good")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Good {

    @Id
    private String id;

    private String name;

    private String description;

    private double price;

    private int amount;

    @Enumerated(EnumType.STRING)
    private FoodType type;

    private Date start_time;

    private Date end_time;
}

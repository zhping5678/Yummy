package com.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.util.GoodState;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String description;

    private double price;

    private int amount;

    private String type;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date start_time;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date end_time;

    @Enumerated(EnumType.STRING)
    private GoodState state;
}

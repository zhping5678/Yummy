package com.model;

import com.util.UserState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer{

    @Id
    private String email;

    private String password;

    private int level;

    @Enumerated(EnumType.STRING)
    private UserState state;//用户的状态，未激活/生效使用中/已注销

    private String activecode;//激活码

}
package com.vo;

import com.model.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerVO {

    private String email;
    private String name;
    private String tel;

    private int level;

    private List<Account> accounts;

}

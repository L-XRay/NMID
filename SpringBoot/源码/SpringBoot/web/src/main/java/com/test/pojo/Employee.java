package com.test.pojo;

//员工

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Employee {
    private Integer ID;
    private String name;
    private int sex;
    private String email;
    private Department department;
    private Date birth;

    public Employee(Integer ID, String name, int sex, String email, Department department) {
        this.ID = ID;
        this.name = name;
        this.sex = sex;
        this.email = email;
        this.department = department;
        this.birth = new Date();
    }
}

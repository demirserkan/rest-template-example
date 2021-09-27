package com.demirserkan.resttemplateexample.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    private Long studentNo;
    private String fullName;
    private String gender;
    private int age;
}

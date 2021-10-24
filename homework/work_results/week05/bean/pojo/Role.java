package com.geek.homework.week05.bean.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Role {

    private Integer id;//角色id
    private String roleName;//角色名称
}

package com.geek.homework.week05.bean.pojo;

import java.util.Date;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    private Integer id;//主键id
    private String username;//名称
    private String password;//用户名
    private Date createTime;//创建时间
    private Date modifyTime;//更新时间
    private Role role;//所属角色
}

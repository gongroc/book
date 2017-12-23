package org.book.rpc.dubbo.swagger.entity;

import io.swagger.annotations.ApiModelProperty;

public class Customer {

    @ApiModelProperty(value = "用户昵称", example = "Rico")
    private String nickname;

    @ApiModelProperty(value = "用户年龄", example = "18")
    private int age;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

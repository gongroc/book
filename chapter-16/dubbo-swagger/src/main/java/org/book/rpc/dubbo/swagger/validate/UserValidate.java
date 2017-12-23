package org.book.rpc.dubbo.swagger.validate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "参数描述")
public class UserValidate {

    @ApiModelProperty(value = "用户昵称", required = true, example = "张三")
    private String nickname;

    @ApiModelProperty(value = "用户年龄", required = true, example = "20")
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

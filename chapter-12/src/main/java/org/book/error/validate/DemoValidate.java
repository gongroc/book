package org.book.error.validate;

import org.hibernate.validator.constraints.NotEmpty;

public class DemoValidate {

    @NotEmpty(message = "用户名不能为空")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

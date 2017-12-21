package org.book.validate.validate;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

public class AccountValidate {

    @NotBlank(message = "用户名不能为空", groups = {PUSH.class, MODIFY.class})
    @Pattern(regexp = "^[a-z0-9_-]{3,15}$", message = "用户名格式错误")
    private String name;

    @NotNull(message = "年龄不能为空", groups = PUSH.class)
    @Min(value = 18, message = "最小年龄为18")
    private Integer age;

    @DecimalMax(value = "100", message = "金额不能大于100")
    private Double price;

    @AssertFalse(message = "不能为false")
    private boolean male;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Past(message = "应该提供之前的日期")
    private Date birthday;

    @Email(message = "email格式错误")
    private String email;

    @Size(min = 10, max = 1000, message = "内容长度应该在10至1000个字之间")
    private String content;

    public interface PUSH {}

    public interface MODIFY {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean isMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

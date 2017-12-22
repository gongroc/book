package org.book.error.controller;

import org.book.error.exception.BusinessException;
import org.book.error.validate.DemoValidate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {

    @RequestMapping(value = "err")
    public void error() {
        throw new BusinessException(400, "错误信息");
    }

    @ResponseBody
    @RequestMapping(value = "err/validate")
    public String error2(@Validated DemoValidate validate){
        return "success : "+validate.getName();
    }

}

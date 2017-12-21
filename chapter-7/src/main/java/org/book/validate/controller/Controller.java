package org.book.validate.controller;

import org.book.validate.validate.AccountValidate;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
public class Controller {

    @RequestMapping(value = "/")
    public Object test(@Validated({AccountValidate.PUSH.class, AccountValidate.MODIFY.class}) AccountValidate validate, BindingResult result, HttpServletResponse response) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            response.setStatus(500);
            return errors;
        } else {
            return "success";
        }

    }
}

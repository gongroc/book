package org.book.error.configuration;

import com.alibaba.fastjson.JSON;
import org.book.error.entity.ExceptionEntity;
import org.book.error.exception.BasicException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BasicException.class)
    public ModelAndView errorHandler(HttpServletRequest request, BasicException exception,
                                     HttpServletResponse response) {
        ExceptionEntity entity = new ExceptionEntity();
        entity.setMessage(exception.getMessage());
        entity.setPath(request.getRequestURI());
        entity.setCode(exception.getCode());
        entity.setMessage(exception.getMessage());
        entity.setPath(request.getRequestURI());
        entity.setError(exception.getClass().getSimpleName());
        if (!(request.getHeader("accept").contains("application/json")
                || (request.getHeader("X-Requested-With") != null
                && request.getHeader("X-Requested-With").contains("XMLHttpRequest")))) {
            ModelAndView modelAndView = new ModelAndView("error");
            modelAndView.addObject("exception", entity);
            return modelAndView;
        } else {
            try {
                response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                response.setCharacterEncoding("UTF-8");
                response.setHeader("Content-Type", "application/json");
                response.getWriter().write(JSON.toJSONString(entity));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    public ExceptionEntity validExceptionHandler(BindException exception, HttpServletRequest request, HttpServletResponse response){
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        Map<String,String> errors = new HashMap<>();
        for (FieldError error:fieldErrors){
            errors.put(error.getField(),error.getDefaultMessage());
        }
        ExceptionEntity entity = new ExceptionEntity();
        entity.setMessage(JSON.toJSONString(errors));
        entity.setPath(request.getRequestURI());
        entity.setCode(500);
        entity.setError(exception.getClass().getSimpleName());
        response.setStatus(500);
        return entity;
    }

}

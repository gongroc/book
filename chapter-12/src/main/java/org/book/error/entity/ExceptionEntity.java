package org.book.error.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class ExceptionEntity {

    private static final long serialVersionUID = 1L;

    private String message;

    private int code;

    private String error;

    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date timestamp = new Date();

    private String path;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

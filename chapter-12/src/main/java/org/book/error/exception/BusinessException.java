package org.book.error.exception;

public class BusinessException extends BasicException {

    private static final long serialVersionUID = 1L;

    public BusinessException(int code, String message) {
        super(code, message);
    }

}

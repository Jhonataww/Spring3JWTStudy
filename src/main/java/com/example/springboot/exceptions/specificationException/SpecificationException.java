package com.example.springboot.exceptions.specificationException;

import java.util.Date;

public class SpecificationException extends Exception{

    private Date dateError;
    private String message;
    private Object fild;

    public SpecificationException() {
    }

    public SpecificationException(Date dateError, String message, Object fild) {
        this.dateError = dateError;
        this.message = message;
        this.fild = fild;
    }

    public SpecificationException(String message, Date dateError, String message1, Object fild) {
        super(message);
        this.dateError = dateError;
        this.message = message1;
        this.fild = fild;
    }

    public SpecificationException(String message, Throwable cause, Date dateError, String message1, Object fild) {
        super(message, cause);
        this.dateError = dateError;
        this.message = message1;
        this.fild = fild;
    }

    public SpecificationException(Throwable cause, Date dateError, String message, Object fild) {
        super(cause);
        this.dateError = dateError;
        this.message = message;
        this.fild = fild;
    }

    public SpecificationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Date dateError, String message1, Object fild) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.dateError = dateError;
        this.message = message1;
        this.fild = fild;
    }

    public Date getDateError() {
        return dateError;
    }

    public void setDateError(Date dateError) {
        this.dateError = dateError;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getFild() {
        return fild;
    }

    public void setFild(Object fild) {
        this.fild = fild;
    }


    @Override
    public String toString() {
        return "SpecificationException{" +
                "dateError=" + dateError +
                ", message='" + message + '\'' +
                ", value=" + fild +
                '}';
    }
}

package com.converter.task.exception;

import lombok.Data;

@Data
public class CustomException extends RuntimeException  {
	
	private String code;
	private String info;

    public CustomException(String code, String message) {
        super(message);
        this.setCode(code);
    }
    public CustomException(String code, String info, String message) {
        super(message);
        this.setCode(code);
        this.setInfo(info);
    }

    public CustomException(String code, String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause ,enableSuppression,writableStackTrace);
        this.setCode(code);
    }

    

	  
}

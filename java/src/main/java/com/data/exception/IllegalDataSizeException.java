package com.data.exception;

/**
 * @author [wangjiahui]
 * @ClassName: IllegalDataSizeException
 * @CreateDate: [2019-05-20 14:57]
 * @Description: [TODO]
 * @version: [V1.0]
 */
public class IllegalDataSizeException extends RuntimeException {

    public IllegalDataSizeException(){super();}

    public IllegalDataSizeException(String message){super(message);}

    public IllegalDataSizeException(String message, Throwable t) {super(message,t);}

    public IllegalDataSizeException(Throwable t) {super(t);}
}

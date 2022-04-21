package ru.itis.pashin.websiteservice.exception;

/**
 * @author <a href="mailto:ruslan.pashin@waveaccess.ru">Ruslan Pashin</a>
 */
public class LoanException extends RuntimeException{

    public LoanException(ExceptionReason exceptionReason) {
        super(exceptionReason.getReason());
    }

    public LoanException(ExceptionReason exceptionReason, Throwable cause) {
        super(exceptionReason.getReason(), cause);
    }
}

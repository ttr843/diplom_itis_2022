package ru.itis.pashin.website.common.model.loan.exception;


public class LoanException extends RuntimeException {

    public LoanException(ExceptionReason exceptionReason) {
        super(exceptionReason.getReason());
    }

    public LoanException(ExceptionReason exceptionReason, Throwable cause) {
        super(exceptionReason.getReason(), cause);
    }
}

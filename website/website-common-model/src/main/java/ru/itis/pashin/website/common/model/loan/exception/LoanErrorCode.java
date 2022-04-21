package ru.itis.pashin.website.common.model.loan.exception;


public enum LoanErrorCode implements ExceptionReason {
    KAFKA_LOAN_SENDING_ERROR,
    REFLECTIVE_CALL_ERROR;

    @Override
    public String getReason() {
        return this.name();
    }
}

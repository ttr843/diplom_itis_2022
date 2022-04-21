package ru.itis.pashin.websiteservice.exception;

/**
 * @author <a href="mailto:ruslan.pashin@waveaccess.ru">Ruslan Pashin</a>
 */
public enum LoanErrorCode implements ExceptionReason {
    KAFKA_LOAN_SENDING_ERROR,
    REFLECTIVE_CALL_ERROR;

    @Override
    public String getReason() {
        return this.name();
    }
}

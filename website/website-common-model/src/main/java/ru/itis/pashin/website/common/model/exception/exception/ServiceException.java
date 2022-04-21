package ru.itis.pashin.website.common.model.exception.exception;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.itis.pashin.website.common.model.exception.enumeration.ServiceErrorCode;


@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@Getter
@Setter
public class ServiceException extends RuntimeException {

    private final String serviceErrorCode;

    public ServiceException(ServiceErrorCode clientErrorCode, Object... args) {
        super(MessageFormatter.arrayFormat(clientErrorCode.getMessage(), args).getMessage());
        this.serviceErrorCode = clientErrorCode.getMessage();
    }

    public ServiceException(ServiceErrorCode clientErrorCode) {
        super(clientErrorCode.getMessage());
        this.serviceErrorCode = clientErrorCode.getMessage();
    }
}

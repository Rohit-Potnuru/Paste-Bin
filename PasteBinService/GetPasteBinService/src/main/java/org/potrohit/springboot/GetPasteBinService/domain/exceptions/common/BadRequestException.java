package org.potrohit.springboot.GetPasteBinService.domain.exceptions.common;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class BadRequestException extends RuntimeException {


    String responseMessage = null;

    public BadRequestException(final String errorMessage) {
        super(errorMessage);
        log.error("Error: " + errorMessage);
    }

    public BadRequestException(final String errorMessage, final String responseMessage) {
        super(errorMessage);
        this.responseMessage = responseMessage;
        log.error("Error: " + errorMessage);
    }

    public BadRequestException(final String errorMessage, final Throwable throwable) {
        super(errorMessage, throwable);
        log.error("Error: " + errorMessage + " ThrowableError: " + throwable.toString());
    }

    public BadRequestException(final String errorMessage, final Throwable throwable, final String responseMessage) {
        super(errorMessage, throwable);
        this.responseMessage = responseMessage;
        log.error("Error: " + errorMessage + " ThrowableError: " + throwable.toString());
    }
}

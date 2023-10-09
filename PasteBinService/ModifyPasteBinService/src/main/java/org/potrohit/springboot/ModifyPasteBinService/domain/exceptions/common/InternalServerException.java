package org.potrohit.springboot.ModifyPasteBinService.domain.exceptions.common;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class InternalServerException extends RuntimeException{
    public InternalServerException(final String errorMessage) {
        super(errorMessage);
        log.error("Error: " + errorMessage);
    }
    public InternalServerException(final String errorMessage, final Throwable throwable) {
        super(errorMessage, throwable);
        log.error("Error: " + errorMessage + " ThrowableError: " + throwable.toString());
    }
}

package org.potrohit.springboot.GetPasteBinService.domain.exceptions.common;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class PasteBinURLNotfoundExpection extends RuntimeException{
    public PasteBinURLNotfoundExpection(final String errorMessage) {
        super(errorMessage);
        log.error("Error: " + errorMessage);
    }
    public PasteBinURLNotfoundExpection(final String errorMessage, final Throwable throwable) {
        super(errorMessage, throwable);
        log.error("Error: " + errorMessage + " ThrowableError: " + throwable.toString());
    }
}

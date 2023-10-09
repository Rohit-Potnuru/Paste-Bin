package org.potrohit.springboot.ModifyPasteBinService.domain.exceptions;

public class NonRetryableException extends RuntimeException {
    public NonRetryableException(final String errorMessage) {
        super(errorMessage);
    }

    public NonRetryableException(final Throwable throwable) {
        super(throwable);
    }
}

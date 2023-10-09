package org.potrohit.springboot.GetPasteBinService.domain.exceptions;

import org.potrohit.springboot.GetPasteBinService.domain.exceptions.common.BadRequestException;
import org.potrohit.springboot.GetPasteBinService.domain.exceptions.common.InternalServerException;
import org.potrohit.springboot.GetPasteBinService.domain.exceptions.common.PasteBinURLNotfoundExpection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PasteBinURLExpection {
    @ExceptionHandler(value = PasteBinURLNotfoundExpection.class)
    public ResponseEntity<Object> exception(PasteBinURLNotfoundExpection exception) {
        return new ResponseEntity<>("URL not Found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = InternalServerException.class)
    public ResponseEntity<Object> exception(InternalServerException exception) {
        return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<Object> exception(BadRequestException exception) {
        return new ResponseEntity<>("BadRequest was made", HttpStatus.BAD_REQUEST);
    }
}
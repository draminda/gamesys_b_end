package com.gamesys.news.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Raminda
 * @apiNote Exception Controller
 */
@RestControllerAdvice
@Component
public class ExceptionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);

    /**
     * @apiNote General
     * @param e general exception
     * @return ResponseEntity<String>
     */
    @ExceptionHandler({Exception.class})
    public ResponseEntity<String> handleException(final Exception e) {
        LOGGER.error("handleException.Exception.msg : {}",e.getMessage());
        return  new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * @apiNote parm based
     * @param e MethodArgumentNotValidException exception
     * @return Map<String, String>
     */
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleException(MethodArgumentNotValidException e) {
        LOGGER.error("handleException.MethodArgumentNotValidException.msg : {}",e.getMessage());
        return new HashMap<>();
    }

    /**
     * @apiNote sql based
     * @param e DataAccessException
     * @return  ResponseEntity<String>
     */
    @ExceptionHandler(DataAccessException.class)
    public  ResponseEntity<String> handleException( SQLException e) {
        LOGGER.error("handleException.DataAccessException.msg : {}",e.getMessage());
        LOGGER.error("handleException.DataAccessException.State : {}",e.getSQLState());
        LOGGER.error("handleException.DataAccessException.Code : {}",e.getErrorCode());
       return  new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);

    }
}

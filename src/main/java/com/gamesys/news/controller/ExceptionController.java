package com.gamesys.news.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
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
     * @param e
     * @return
     */
    @ExceptionHandler({Exception.class})
    public ResponseEntity<String> handleException(final Exception e) {
        LOGGER.error("handleException.msg : ",e.getMessage());
        return  new ResponseEntity<String>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * @apiNote parm based
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleException(MethodArgumentNotValidException e) {
        LOGGER.error("handleException.msg : ",e.getMessage());
        return new HashMap<>();
    }

    /**
     * @apiNote sql based
     * @param e
     * @return
     */
    @ExceptionHandler(DataAccessException.class)
    public  ResponseEntity<String> handleException( SQLException e) {
        LOGGER.error("handleException.msg : ",e.getMessage());
        LOGGER.error("handleException.State : ",e.getSQLState());
        LOGGER.error("handleException.Code : ",e.getErrorCode());
       return  new ResponseEntity<String>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);

    }
}

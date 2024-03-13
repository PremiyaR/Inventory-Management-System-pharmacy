package com.pilot.inventory.exception;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Configuration
public class GlobalExceptionHandler {
    @ExceptionHandler(DuplicateName.class)
    public ResponseEntity<Object> handleDuplicateName(DuplicateName e) {
        String errorMessage = "Duplicate entry found";
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntryAlreadyExists.class)
    public ResponseEntity<Object> handleEntryAlreadyExistsException(EntryAlreadyExists e){
        String errorMessage="No changes applied as the updated category is the same as before";
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoEntriesFound.class)
    public ResponseEntity<Object> handleNoEntriesFoundCategoryException(NoEntriesFound e)
    {
        String errorMessage="No entries found";
        return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ItemAlreadyExistsException.class)
    public ResponseEntity<Object> handleCategoryNotFoundException (ItemAlreadyExistsException e)
    {
        String errorMessage="Item already exists in same/different ID";
        return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
    }


}

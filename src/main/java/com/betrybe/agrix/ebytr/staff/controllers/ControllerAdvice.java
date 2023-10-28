package com.betrybe.agrix.ebytr.staff.controllers;

import com.betrybe.agrix.ebytr.staff.controllers.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;


/**
 * class.
 */

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<String> handleException(RuntimeException error) {

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage());
  }

//  @ExceptionHandler(Exception.class)
//  public ResponseEntity<ResponseDto> handleException(Exception error) {
//    ResponseDto responseDto = new ResponseDto(null, error.getMessage());
//    return ResponseEntity.status(500).body(responseDto);
//  }


}

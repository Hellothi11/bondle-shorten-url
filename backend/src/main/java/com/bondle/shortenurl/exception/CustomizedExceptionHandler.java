package com.bondle.shortenurl.exception;

import com.bondle.shortenurl.dto.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author nguyen.tam.thi at 10:35 PM 4/1/21
 */

@ControllerAdvice
@RestController
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler({NotFoundException.class})
  @ResponseBody
  public ResponseEntity handleNotFoundException(NotFoundException exception) {
    Response response = Response.notFound();
    response.addErrorMsgToResponse(exception.getMessage(), exception);
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

}

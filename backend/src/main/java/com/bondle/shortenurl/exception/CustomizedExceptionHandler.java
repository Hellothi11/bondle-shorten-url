package com.bondle.shortenurl.exception;

import com.bondle.shortenurl.dto.response.Response;
import com.bondle.shortenurl.dto.response.ResponseError;
import com.bondle.shortenurl.util.DateTimeUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author nguyen.tam.thi at 10:35 PM 4/1/21
 */

@ControllerAdvice
@RestController
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler({NotFoundException.class})
  public Response handleNotFoundException(NotFoundException exception) {
    Response response = Response.notFound();
    response.addErrorMsgToResponse(exception.getMessage(), exception);
    return response;
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatus status,
      WebRequest request) {
    Response response = Response.badRequest();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      ResponseError responseError = new ResponseError()
          .setMessage(error.getDefaultMessage())
          .setTimestamp(DateTimeUtil.now())
          .setFieldName(((FieldError) error).getField());
      response.addError(responseError);
    });
    return ResponseEntity.badRequest().body(response);
  }

}

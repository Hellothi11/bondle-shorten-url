package com.bondle.shortenurl.dto.response;

import com.bondle.shortenurl.util.DateTimeUtil;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;


/**
 * @author nguyen.tam.thi at 5:14 PM 4/1/21
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class Response<T> {

  private Status status;
  private T payload;
  private List<Object> errors;
  private Object metadata;

  public static <T> Response<T> badRequest() {
    Response<T> response = new Response<>();
    response.setStatus(Status.BAD_REQUEST);
    return response;
  }

  public static <T> Response<T> ok(T payload) {
    Response<T> response = new Response<>();
    response.setStatus(Status.OK);
    response.setPayload(payload);
    return response;
  }

  public static <T> Response<T> ok() {
    Response<T> response = new Response<>();
    response.setStatus(Status.OK);
    return response;
  }

  public static <T> Response<T> unauthorized() {
    Response<T> response = new Response<>();
    response.setStatus(Status.UNAUTHORIZED);
    return response;
  }

  public static <T> Response<T> validationException() {
    Response<T> response = new Response<>();
    response.setStatus(Status.VALIDATION_EXCEPTION);
    return response;
  }

  public static <T> Response<T> wrongCredentials() {
    Response<T> response = new Response<>();
    response.setStatus(Status.WRONG_CREDENTIALS);
    return response;
  }

  public static <T> Response<T> accessDenied() {
    Response<T> response = new Response<>();
    response.setStatus(Status.ACCESS_DENIED);
    return response;
  }

  public static <T> Response<T> exception() {
    Response<T> response = new Response<>();
    response.setStatus(Status.EXCEPTION);
    return response;
  }

  public static <T> Response<T> notFound() {
    Response<T> response = new Response<>();
    response.setStatus(Status.NOT_FOUND);
    return response;
  }

  public void addErrorMsgToResponse(String errorMsg, Exception ex) {
    ResponseError responseError = new ResponseError()
        .setDetails(errorMsg)
        .setMessage(ex.getMessage())
        .setTimestamp(DateTimeUtil.now());
    addError(responseError);
  }

  public void addError(ResponseError responseError) {
    if (errors == null) {
      errors = new ArrayList<>();
    }
    errors.add(responseError);
  }

  public enum Status {
    OK, BAD_REQUEST, UNAUTHORIZED, VALIDATION_EXCEPTION, EXCEPTION, WRONG_CREDENTIALS, ACCESS_DENIED, NOT_FOUND
  }

}

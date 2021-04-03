package com.bondle.shortenurl.exception;

/**
 * @author nguyen.tam.thi at 10:38 PM 4/1/21
 */
public class NotFoundException extends RuntimeException {
  public NotFoundException(String message) {
    super(message);
  }
}

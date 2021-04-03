package com.bondle.shortenurl.util;

import java.util.UUID;

/**
 * @author nguyen.tam.thi at 5:50 PM 4/1/21
 */
public class ShortenUrlHash {

  public static String generateHash(String url, long id, int length) {
    return String.format("ABC_%d", id);
  }
}

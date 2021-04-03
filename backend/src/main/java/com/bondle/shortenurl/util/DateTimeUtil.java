package com.bondle.shortenurl.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author nguyen.tam.thi at 5:16 PM 4/1/21
 */
public class DateTimeUtil {

  private static SimpleDateFormat _sdf;

  private static SimpleDateFormat getDateFormat() {
    if (_sdf == null) {
      _sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
      _sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
    }
    return _sdf;
  }

  public static Date now() {
    return new Date();
  }


  public static String formattedNow() {
    return getDateFormat().format(now());
  }
}

package com.bondle.shortenurl.dto.response;

import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author nguyen.tam.thi at 5:15 PM 4/1/21
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class ResponseError {
  private Date timestamp;
  private String message;
  private String details;
}

package com.bondle.shortenurl.dto.request;

import com.bondle.shortenurl.dto.validator.URLConstraint;
import javax.validation.Valid;
import lombok.Data;

/**
 * @author nguyen.tam.thi at 8:56 PM 4/1/21
 */
@Data
public class CreateShortUrlRequest {

  @Valid
  @URLConstraint
  private String url;

  private String uuid;
}

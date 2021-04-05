package com.bondle.shortenurl.dto.request;

import com.bondle.shortenurl.dto.validator.URLConstraint;
import javax.validation.Valid;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author nguyen.tam.thi at 8:56 PM 4/1/21
 */
@Data
public class CreateShortUrlRequest {

  @Valid
  @URLConstraint
  @Length(max = 2048, message = "URL is too long. Maximum 2048 length.")
  private String url;

  private String uuid;
}

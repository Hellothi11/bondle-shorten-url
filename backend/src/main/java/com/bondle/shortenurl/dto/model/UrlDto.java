package com.bondle.shortenurl.dto.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author nguyen.tam.thi at 4:50 PM 4/1/21
 */
@Data
@Accessors(chain = true)
public class UrlDto {
  private Long id;
  private String longUrl;
  private String shortUrl;
}

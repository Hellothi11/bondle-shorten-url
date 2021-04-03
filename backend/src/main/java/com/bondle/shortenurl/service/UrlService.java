package com.bondle.shortenurl.service;

import com.bondle.shortenurl.dto.model.UrlDto;
import com.bondle.shortenurl.dto.request.CreateShortUrlRequest;

/**
 * @author nguyen.tam.thi at 4:46 PM 4/1/21
 */
public interface UrlService {
  UrlDto create(String url);
  String findUrlByHash(String hash);
}

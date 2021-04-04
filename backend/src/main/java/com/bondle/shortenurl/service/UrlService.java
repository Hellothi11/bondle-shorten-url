package com.bondle.shortenurl.service;

import com.bondle.shortenurl.dto.model.UrlDto;
import java.util.List;

/**
 * @author nguyen.tam.thi at 4:46 PM 4/1/21
 */
public interface UrlService {

  UrlDto create(String url, String uuid);

  String findUrlByHash(String hash);

  List<UrlDto> findAllUrlsByUuid(String uuid);

  void deleteById(Long id);
}

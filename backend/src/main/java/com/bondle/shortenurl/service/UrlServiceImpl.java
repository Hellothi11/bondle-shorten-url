package com.bondle.shortenurl.service;

import com.bondle.shortenurl.config.PropertiesConfig;
import com.bondle.shortenurl.dto.mapper.UrlMapper;
import com.bondle.shortenurl.dto.model.UrlDto;
import com.bondle.shortenurl.exception.NotFoundException;
import com.bondle.shortenurl.model.Url;
import com.bondle.shortenurl.repository.UrlRepository;
import com.bondle.shortenurl.util.ShortenUrlHash;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author nguyen.tam.thi at 4:47 PM 4/1/21
 */
@Service
public class UrlServiceImpl implements UrlService {

  @Autowired
  private UrlRepository urlRepository;

  @Autowired
  private PropertiesConfig propertiesConfig;

  @Override
  public UrlDto create(String url) {
    long randomNum = ThreadLocalRandom.current().nextLong(0L, 10000L);
    Url entity = new Url();
    entity.setLongUrl(url);
    entity.setHash(ShortenUrlHash.generateHash(url, randomNum, 6));
    return UrlMapper.INSTANCE.urlToUrlDto(urlRepository.save(entity))
        .setShortUrl(String.format(propertiesConfig.getConfigValue("bondle.webUrl.hash"), entity.getHash()));
  }

  @Override
  public String findUrlByHash(String hash) {
    return urlRepository.findByHash(hash).map(Url::getLongUrl)
        .orElseThrow(() -> new NotFoundException("Unable to find URL to redirect to."));
  }
}

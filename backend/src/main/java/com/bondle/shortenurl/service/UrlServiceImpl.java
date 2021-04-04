package com.bondle.shortenurl.service;

import com.bondle.shortenurl.config.PropertiesConfig;
import com.bondle.shortenurl.dto.mapper.UrlMapper;
import com.bondle.shortenurl.dto.model.UrlDto;
import com.bondle.shortenurl.exception.NotFoundException;
import com.bondle.shortenurl.model.Url;
import com.bondle.shortenurl.repository.UrlRepository;
import com.bondle.shortenurl.util.Hashids;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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

  @Autowired
  private UrlMapper urlMapper;

  @Autowired
  private Hashids hashids;

  @Override
  public UrlDto create(String url, String uuid) {
    long id = urlRepository.generateNextId();
    Url entity = new Url();
    entity.setId(id);
    entity.setLongUrl(url);
    entity.setHash(hashids.encrypt(id));
    entity.setUuid(uuid);
    return urlMapper.urlToUrlDto(urlRepository.save(entity))
        .setShortUrl(String.format(propertiesConfig.getConfigValue("bondle.webUrl.hash"), entity.getHash()));
  }

  @Override
  public String findUrlByHash(String hash) {
    return urlRepository.findByHash(hash).map(Url::getLongUrl)
        .orElseThrow(() -> new NotFoundException("Unable to find URL to redirect to."));
  }

  @Override
  public List<UrlDto> findAllUrlsByUuid(String uuid) {
    return urlMapper.listUrlsToListDtos(urlRepository.findAllByUuidOrderByCreatedAtDesc(uuid));
  }

  @Override
  public void deleteById(Long id) {
    urlRepository.deleteById(id);
  }
}

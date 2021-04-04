package com.bondle.shortenurl.dto.mapper;

import com.bondle.shortenurl.config.PropertiesConfig;
import com.bondle.shortenurl.dto.model.UrlDto;
import com.bondle.shortenurl.model.Url;
import java.util.List;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author nguyen.tam.thi at 5:02 PM 4/1/21
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public abstract class UrlMapper {

  @Autowired
  private PropertiesConfig propertiesConfig;


  @Mapping(source = "id", target = "id")
  @Mapping(source = "longUrl", target = "longUrl")
  @Mapping(target = "shortUrl", ignore = true)
  public abstract UrlDto urlToUrlDto(Url url);

  public abstract List<UrlDto> listUrlsToListDtos(List<Url> url);

  @AfterMapping
  void afterToUrlDto(Url url, @MappingTarget UrlDto result) {
    result.setShortUrl(String.format(propertiesConfig.getConfigValue("bondle.webUrl.hash"), url.getHash()));
  }
}

package com.bondle.shortenurl.dto.mapper;

import com.bondle.shortenurl.dto.model.UrlDto;
import com.bondle.shortenurl.model.Url;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * @author nguyen.tam.thi at 5:02 PM 4/1/21
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UrlMapper {
  UrlMapper INSTANCE = Mappers.getMapper(UrlMapper.class);

  @Mapping(source = "id", target = "id")
  @Mapping(source = "longUrl", target = "longUrl")
  @Mapping(target = "shortUrl", ignore = true)
  UrlDto urlToUrlDto(Url url);
}

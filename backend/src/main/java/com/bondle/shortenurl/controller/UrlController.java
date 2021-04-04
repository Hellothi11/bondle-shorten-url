package com.bondle.shortenurl.controller;

import com.bondle.shortenurl.dto.model.UrlDto;
import com.bondle.shortenurl.dto.request.CreateShortUrlRequest;
import com.bondle.shortenurl.dto.response.Response;
import com.bondle.shortenurl.service.UrlService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nguyen.tam.thi at 4:41 PM 4/1/21
 */
@RestController
@RequestMapping("/url")
@Validated
public class UrlController {

  @Autowired
  private UrlService urlService;

  @PostMapping
  public Response<UrlDto> create(@Valid @RequestBody CreateShortUrlRequest request) {
    return Response.ok(urlService.create(request.getUrl(), request.getUuid()));
  }

  @GetMapping("/{uuid}")
  public Response<List<UrlDto>> findAll(@PathVariable String uuid) {
    return Response.ok(urlService.findAllUrlsByUuid(uuid));
  }

  @DeleteMapping("/{id}")
  public Response<Void> delete(@PathVariable Long id) {
    urlService.deleteById(id);
    return Response.ok();
  }
}




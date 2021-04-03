package com.bondle.shortenurl.controller;

import com.bondle.shortenurl.dto.model.UrlDto;
import com.bondle.shortenurl.dto.request.CreateShortUrlRequest;
import com.bondle.shortenurl.dto.response.Response;
import com.bondle.shortenurl.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nguyen.tam.thi at 4:41 PM 4/1/21
 */
@RestController
@RequestMapping("/url")
public class UrlController {

  @Autowired
  private UrlService urlService;

  @PostMapping("/create")
  public Response<UrlDto> create(@RequestBody CreateShortUrlRequest request) {
    return Response.ok(urlService.create(request.getUrl()));
  }

}




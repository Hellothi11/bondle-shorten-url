package com.bondle.shortenurl.controller;

import com.bondle.shortenurl.service.UrlService;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nguyen.tam.thi at 6:17 PM 4/2/21
 */
@RestController
public class HashController {

  @Autowired
  private UrlService urlService;

  @GetMapping("/{hash}")
  public void goRedirect(@PathVariable String hash, HttpServletResponse response) throws IOException {
    String url = urlService.findUrlByHash(hash);
    response.sendRedirect(url);
  }
}

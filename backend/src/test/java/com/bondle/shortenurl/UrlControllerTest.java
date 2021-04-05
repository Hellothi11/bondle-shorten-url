package com.bondle.shortenurl;

import static org.assertj.core.api.Assertions.assertThat;

import com.bondle.shortenurl.controller.UrlController;
import com.bondle.shortenurl.dto.model.UrlDto;
import com.bondle.shortenurl.dto.request.CreateShortUrlRequest;
import com.bondle.shortenurl.dto.response.Response;
import com.bondle.shortenurl.dto.response.ResponseError;
import com.bondle.shortenurl.service.UrlService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UrlController.class)
public class UrlControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UrlService userService;


  @Test
  public void createUrlSuccess() throws Exception {
    String URI = "/url";
    UrlDto url = new UrlDto();
    url.setId(1L);
    url.setShortUrl("http://localhost:8080/abc");
    url.setLongUrl("https://google.com.vn");

    CreateShortUrlRequest request = new CreateShortUrlRequest();
    request.setUuid("uuid");
    request.setUrl("https://google.com.vn");
    String jsonInput = this.convertToJson(request);
    String jsonExpected = this.convertToJson(Response.ok(url));

    Mockito.when(userService.create(Mockito.any(), Mockito.any())).thenReturn(url);
    MvcResult mvcResult = this.mockMvc
        .perform(MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
        .andReturn();

    MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
    String jsonOutput = mockHttpServletResponse.getContentAsString();
    assertThat(jsonExpected).isEqualTo(jsonOutput);
    Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
  }

  @Test
  public void createUrlFailed() throws Exception {
    String URI = "/url";
    UrlDto url = new UrlDto();

    CreateShortUrlRequest request = new CreateShortUrlRequest();
    request.setUuid("uuid");
    request.setUrl("xyz");
    String jsonInput = this.convertToJson(request);

    List<Object> errors = new ArrayList<>();
    errors.add(new ResponseError().setMessage("Invalid URL").setFieldName("url"));
    Response error = Response.badRequest().setErrors(errors);

    Mockito.when(userService.create(Mockito.any(), Mockito.any())).thenReturn(url);
    MvcResult mvcResult = this.mockMvc
        .perform(MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
        .andReturn();

    MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
    String jsonOutput = mockHttpServletResponse.getContentAsString();
    Map<String, Object> outputObject = this.convertToObject(jsonOutput);

    List<Object> outputErrors = (List<Object>) outputObject.get("errors");
    assertThat(outputErrors.size()).isEqualTo(1);
    Map<String, Object> outputError = (Map<String, Object>) outputErrors.get(0);
    assertThat(outputError.get("message")).isEqualTo("Invalid URL");
    Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), mockHttpServletResponse.getStatus());
  }

  private String convertToJson(Object ticket) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.writeValueAsString(ticket);
  }

  private Map convertToObject(String json) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(json, Map.class);
  }

}

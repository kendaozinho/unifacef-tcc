package com.unifacef.tcc.controller.base;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseControllerTest {
  @LocalServerPort
  protected Integer port = 0;

  @Autowired
  private WebApplicationContext context;

  @Autowired
  private ObjectMapper mapper;

  private MockMvc mock;

  @BeforeEach
  private void before() {
    this.mock = MockMvcBuilders.webAppContextSetup(this.context).build();
  }

  protected void getIsOk(String path) throws Throwable {
    this.mock.perform(MockMvcRequestBuilders.get(path).accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  protected void getIsNotFound(String path, String developerMessage) throws Throwable {
    this.mock.perform(MockMvcRequestBuilders.get(path).accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isNotFound())
        .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode", Matchers.is(404)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.developerMessage", Matchers.is(developerMessage)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.userMessage", Matchers.is("Not Found")));
  }

  protected void getIsBadRequest(String path, String developerMessage) throws Throwable {
    this.mock.perform(MockMvcRequestBuilders.get(path).accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode", Matchers.is(400)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.developerMessage", Matchers.is(developerMessage)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.userMessage", Matchers.is("Bad Request")));
  }

  protected void postIsCreated(String path, Object request) throws Throwable {
    this.mock.perform(MockMvcRequestBuilders.post(path).accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(request)))
        .andExpect(MockMvcResultMatchers.status().isCreated());
  }

  protected void postIsUnprocessableEntity(String path, Object request, String developerMessage) throws Throwable {
    this.mock.perform(MockMvcRequestBuilders.post(path).accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(request)))
        .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity())
        .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode", Matchers.is(422)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.developerMessage", Matchers.is(developerMessage)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.userMessage", Matchers.is("Unprocessable Entity")));
  }

  protected void postIsBadRequest(String path, Object request, String developerMessage) throws Throwable {
    this.mock.perform(MockMvcRequestBuilders.post(path).accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(request)))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode", Matchers.is(400)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.developerMessage", Matchers.is(developerMessage)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.userMessage", Matchers.is("Bad Request")));
  }

  protected void postIsConflict(String path, Object request, String developerMessage) throws Throwable {
    this.mock.perform(MockMvcRequestBuilders.post(path).accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(request)))
        .andExpect(MockMvcResultMatchers.status().isConflict())
        .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode", Matchers.is(409)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.developerMessage", Matchers.is(developerMessage)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.userMessage", Matchers.is("Conflict")));
  }

  protected void postIsNotFound(String path, Object request, String developerMessage) throws Throwable {
    this.mock.perform(MockMvcRequestBuilders.post(path).accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(request)))
        .andExpect(MockMvcResultMatchers.status().isNotFound())
        .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode", Matchers.is(404)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.developerMessage", Matchers.is(developerMessage)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.userMessage", Matchers.is("Not Found")));
  }

  protected void putIsOk(String path, Object request) throws Throwable {
    this.mock.perform(MockMvcRequestBuilders.put(path).accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(request)))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  protected void putIsUnprocessableEntity(String path, Object request, String developerMessage) throws Throwable {
    this.mock.perform(MockMvcRequestBuilders.put(path).accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(request)))
        .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity())
        .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode", Matchers.is(422)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.developerMessage", Matchers.is(developerMessage)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.userMessage", Matchers.is("Unprocessable Entity")));
  }

  protected void putIsBadRequest(String path, Object request, String developerMessage) throws Throwable {
    this.mock.perform(MockMvcRequestBuilders.put(path).accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(request)))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode", Matchers.is(400)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.developerMessage", Matchers.is(developerMessage)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.userMessage", Matchers.is("Bad Request")));
  }

  protected void putIsNotFound(String path, Object request, String developerMessage) throws Throwable {
    this.mock.perform(MockMvcRequestBuilders.put(path).accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(request)))
        .andExpect(MockMvcResultMatchers.status().isNotFound())
        .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode", Matchers.is(404)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.developerMessage", Matchers.is(developerMessage)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.userMessage", Matchers.is("Not Found")));
  }

  protected void putIsConflict(String path, Object request, String developerMessage) throws Throwable {
    this.mock.perform(MockMvcRequestBuilders.put(path).accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(request)))
        .andExpect(MockMvcResultMatchers.status().isConflict())
        .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode", Matchers.is(409)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.developerMessage", Matchers.is(developerMessage)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.userMessage", Matchers.is("Conflict")));
  }

  protected void deleteIsNoContent(String path) throws Throwable {
    this.mock.perform(MockMvcRequestBuilders.delete(path).accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isNoContent());
  }

  protected void deleteIsBadRequest(String path, String developerMessage) throws Throwable {
    this.mock.perform(MockMvcRequestBuilders.delete(path).accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode", Matchers.is(400)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.developerMessage", Matchers.is(developerMessage)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.userMessage", Matchers.is("Bad Request")));
  }

  protected void deleteIsNotFound(String path, String developerMessage) throws Throwable {
    this.mock.perform(MockMvcRequestBuilders.delete(path).accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isNotFound())
        .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode", Matchers.is(404)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.developerMessage", Matchers.is(developerMessage)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.userMessage", Matchers.is("Not Found")));
  }

  protected void deleteIsUnprocessableEntity(String path, String developerMessage) throws Throwable {
    this.mock.perform(MockMvcRequestBuilders.delete(path).accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity())
        .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode", Matchers.is(422)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.developerMessage", Matchers.is(developerMessage)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.userMessage", Matchers.is("Unprocessable Entity")));
  }
}

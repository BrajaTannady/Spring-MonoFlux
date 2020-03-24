package com.example.SpringMonoFlux.controller;

import com.example.SpringMonoFlux.TestSpringBootApp;
import com.example.SpringMonoFlux.entity.ListTodo;
import com.example.SpringMonoFlux.repository.ListRepository;
import com.example.SpringMonoFlux.service.api.ListService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
                classes = TestSpringBootApp.class)
class ListControllerTest {
  @MockBean
  ListService listService;

  WebTestClient webTestClient;

  @Test
  void insertList() {
    ListTodo listTodo = ListTodo.builder()
        .id("1")
        .listName("A")
        .status(false)
        .build();
    when(listService.addListTodo(listTodo)).thenReturn(Mono.just(listTodo));
    webTestClient.post()
        .uri("/insertList")
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromObject(listTodo))
        .exchange()
        .expectStatus().isCreated();

    verify(listService, times(1)).addListTodo(listTodo);
  }

  @Test
  void findAllList() {
  }

  @Test
  void findAllListByStatus() {
  }

  @Test
  void updateList() {
  }

  @Test
  void updateListStatusToCompleted() {
  }

  @Test
  void updateListStatusToActive() {
  }

  @Test
  void updateAllStatus() {
  }

  @Test
  void deleteByListName() {
  }

  @Test
  void deleteAll() {
  }
}
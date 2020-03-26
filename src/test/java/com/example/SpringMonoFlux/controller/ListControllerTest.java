package com.example.SpringMonoFlux.controller;

import com.example.SpringMonoFlux.entity.ListTodo;
import com.example.SpringMonoFlux.service.api.ListService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.print.attribute.standard.Media;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebFluxTest(ListController.class)
class ListControllerTest {

  @Autowired
  WebTestClient webTestClient;
  @MockBean
  ListService listService;

  @Test
  void insertList() {
    ListTodo listTodo = ListTodo.builder().build();
    when(listService.addListTodo(listTodo)).thenReturn(Mono.just(listTodo));

    webTestClient.post()
        .uri("/insertList")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .body(Mono.just(listTodo), ListTodo.class)
        .exchange()
        .expectStatus().isOk();
    verify(listService).addListTodo(listTodo);
  }

  @Test
  void findAllList() {
    ListTodo listTodo = ListTodo.builder().build();
    when(listService.findAllList()).thenReturn(Flux.just(listTodo));
    webTestClient.get()
        .uri("/findAllList")
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isOk()
        .expectBodyList(ListTodo.class);
    verify(listService).findAllList();
  }

  @Test
  void findAllListByStatus() {
    ListTodo listTodo = ListTodo.builder()
        .id("1")
        .listName("A")
        .status(false)
        .build();
    when(listService.findAllListByStatus(false)).thenReturn(Flux.just(listTodo));
    webTestClient.get()
        .uri("/findAllListByStatus/{status}", false)
        .exchange()
        .expectStatus().isOk()
        .expectBodyList(ListTodo.class);
    verify(listService).findAllListByStatus(false);
  }

  @Test
  void updateList() {
    String id = "1";
    ListTodo listTodo = ListTodo.builder().build();
    when(listService.updateList(id, listTodo)).thenReturn(Mono.just(listTodo));
    webTestClient.put()
        .uri("/updateListName/{id}", id)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .body(Mono.just(listTodo), ListTodo.class)
        .exchange()
        .expectStatus().isOk()
        .expectBody(ListTodo.class);
    verify(listService).updateList(id, listTodo);
  }

  @Test
  void updateListStatusToCompleted() {
    String id = "1";
    ListTodo listTodo = ListTodo.builder().build();
    when(listService.updateListStatusToCompleted(id)).thenReturn(Mono.just(listTodo));
    webTestClient.put()
        .uri("/updateCompleted/{id}", id)
        .exchange()
        .expectStatus().isOk()
        .expectBody(ListTodo.class);
    verify(listService).updateListStatusToCompleted(id);
  }

  @Test
  void updateListStatusToActive() {
    String id = "1";
    ListTodo listTodo = ListTodo.builder().build();
    when(listService.updateListStatusToActive(id)).thenReturn(Mono.just(listTodo));
    webTestClient.put()
        .uri("/updateActive/{id}", id)
        .exchange()
        .expectStatus().isOk()
        .expectBody(ListTodo.class);
    verify(listService).updateListStatusToActive(id);
  }

  @Test
  void updateAllStatus() {
    ListTodo listTodo = ListTodo.builder().build();
    when(listService.updateAllStatus(false)).thenReturn(Flux.just(listTodo));
    webTestClient.put()
        .uri("/updateAllStatus/{status}", false)
        .exchange()
        .expectStatus().isOk()
        .expectBodyList(ListTodo.class);
    verify(listService).updateAllStatus(false);
  }

  @Test
  void deleteByListName() {
    String id = "1";
    when(listService.deleteByListName(id)).thenReturn(Mono.empty());
    webTestClient.delete()
        .uri("/deleteList/{id}", id)
        .exchange()
        .expectStatus().isOk();
    verify(listService).deleteByListName(id);
  }

  @Test
  void deleteAll() {
    when(listService.deleteAll()).thenReturn(Mono.empty());
    webTestClient.delete()
        .uri("/deleteAll")
        .exchange()
        .expectStatus().isOk();
    verify(listService).deleteAll();
  }
}
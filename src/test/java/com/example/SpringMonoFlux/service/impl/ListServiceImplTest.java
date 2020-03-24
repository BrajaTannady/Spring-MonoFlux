package com.example.SpringMonoFlux.service.impl;

import com.example.SpringMonoFlux.entity.ListTodo;
import com.example.SpringMonoFlux.repository.ListRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class ListServiceImplTest {

  @InjectMocks
  private ListServiceImpl listService;

  @Mock
  private ListRepository listRepository;

  @BeforeEach
  void setUp() {
    initMocks(this);
  }

  @AfterEach
  void tearDown() {
    verifyNoMoreInteractions(listRepository);
  }

  @Test
  void addListTodo() {
    ListTodo listTodo = ListTodo.builder()
        .id("1")
        .listName("Test")
        .status(false)
        .build();
    when(listRepository.save(listTodo)).thenReturn(Mono.just(listTodo));
    StepVerifier.create(listService.addListTodo(listTodo))
        .expectNextCount(1)
        .expectComplete()
        .verify();
    verify(this.listRepository).save(listTodo);
  }

  @Test
  void findAllList() {
    when(listRepository.findAll()).thenReturn(Flux.just(
        ListTodo.builder()
            .id("1")
            .listName("A")
            .status(true)
            .build(),
        ListTodo.builder()
            .id("2")
            .listName("B")
            .status(true)
            .build()));

    StepVerifier.create(listService.findAllList())
        .expectNextCount(2)
        .expectComplete()
        .verify();
    verify(this.listRepository).findAll();
  }

  @Test
  void findAllListByStatus() {
    when(listRepository.findAllByStatus(false)).thenReturn(Flux.just());
    StepVerifier.create(listService.findAllListByStatus(false))
        .expectNextCount(0)
        .expectComplete()
        .verify();
    verify(this.listRepository).findAllByStatus(false);
  }

  @Test
  void updateList() {
    String id = "1";
    ListTodo listTodo =
        ListTodo
            .builder()
            .id("1")
            .listName("A")
            .status(true)
            .build();
    ListTodo updated =
        ListTodo
            .builder()
            .id("1")
            .listName("C")
            .status(true)
            .build();
    when(listRepository.findById(id)).thenReturn(Mono.just(listTodo));
    when(listRepository.save(updated)).thenReturn(Mono.just(updated));
    StepVerifier.create(listService.updateList(id, updated))
        //        .expectNextCount(1)
        .assertNext(t -> assertThat(t).isEqualTo(updated))
        .expectComplete()
        .verify();
    verify(this.listRepository).findById(id);
    verify(this.listRepository).save(updated);
  }

  @Test
  void updateListStatusToCompleted() {
    String id = "1";
    ListTodo listTodo =
        ListTodo
            .builder()
            .id("1")
            .listName("A")
            .status(true)
            .build();
    ListTodo updated =
        ListTodo
            .builder()
            .id("1")
            .listName("A")
            .status(false)
            .build();
    when(listRepository.findById(id)).thenReturn(Mono.just(listTodo));
    when(listRepository.save(updated)).thenReturn(Mono.just(updated));

    StepVerifier.create(listService.updateListStatusToCompleted(id))
        .assertNext(t -> assertThat(t).isEqualTo(updated))
        .expectComplete()
        .verify();
    verify(this.listRepository).findById(id);
    verify(this.listRepository).save(updated);
  }

  @Test
  void updateListStatusToActive() {
    String id = "1";
    ListTodo listTodo =
        ListTodo
            .builder()
            .id("1")
            .listName("A")
            .status(false)
            .build();
    ListTodo updated =
        ListTodo
            .builder()
            .id("1")
            .listName("A")
            .status(true)
            .build();
    when(listRepository.findById(id)).thenReturn(Mono.just(listTodo));
    when(listRepository.save(updated)).thenReturn(Mono.just(updated));

    StepVerifier.create(listService.updateListStatusToCompleted(id))
        .assertNext(t -> assertThat(t).isEqualTo(updated))
        .expectComplete()
        .verify();
    verify(this.listRepository).findById(id);
    verify(this.listRepository).save(updated);
  }

  @Test
  void updateAllStatus() {
    ListTodo listTodo = ListTodo.builder()
        .id("1")
        .listName("A")
        .status(true)
        .build();
    when(listRepository.findAll()).thenReturn(Flux.just(listTodo));
    when(listRepository.save(listTodo)).thenReturn(Mono.just(listTodo));

    StepVerifier.create(listService.updateAllStatus(false))
        .assertNext(t -> assertThat(t).isEqualTo(listTodo))
        .expectComplete()
        .verify();
    verify(this.listRepository).findAll();
    verify(this.listRepository).save(listTodo);
  }

  @Test
  void deleteByListName() {
    String id = "1";
    ListTodo listTodo = ListTodo.builder()
        .id("1")
        .listName("A")
        .status(false)
        .build();
    when(listRepository.deleteById(id)).thenReturn(Mono.empty());
    StepVerifier.create(listService.deleteByListName(id))
        .expectNextCount(0)
        .expectComplete()
        .verify();
    verify(listRepository).deleteById(id);
  }

  @Test
  void deleteAll() {
    ListTodo listTodo = ListTodo.builder()
        .id("1")
        .listName("A")
        .status(false)
        .build();
    when(listRepository.deleteAll()).thenReturn(Mono.empty());
    StepVerifier.create(listService.deleteAll())
        .expectNextCount(0)
        .expectComplete()
        .verify();
    verify(listRepository).deleteAll();
  }
}
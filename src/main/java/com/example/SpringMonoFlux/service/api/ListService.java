package com.example.SpringMonoFlux.service.api;

import com.example.SpringMonoFlux.entity.ListTodo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface ListService {
  Mono<ListTodo> addListTodo(ListTodo listTodo);

  Flux<ListTodo> findAllList();

  Flux<ListTodo> findAllListByStatus(boolean status);

  Mono<ListTodo> updateList(String id, ListTodo updatedList);

  Mono<ListTodo> updateListStatusToCompleted(String id);

  Mono<ListTodo> updateListStatusToActive(String id);

  Flux<ListTodo> updateAllStatus(boolean status);

  Mono<Void> deleteByListName(String id);

  Mono<Void> deleteAll();
}

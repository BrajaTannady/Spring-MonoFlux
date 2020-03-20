package com.example.SpringMonoFlux.service.api;

import com.example.SpringMonoFlux.entity.ListTodo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface ListService {
  Mono<ListTodo> addListTodo(ListTodo listTodo);

  Flux<ListTodo> findAllList();

  Flux<ListTodo> findAllListByStatus(boolean status);

  Mono<ListTodo> updateList(String listName, ListTodo updatedList);

  Mono<ListTodo> updateListStatusToCompleted(String listName);

  Mono<ListTodo> updateListStatusToActive(String listName);

  Mono<Void> deleteByListName(String listName);

  Mono<Void> deleteAll();
}

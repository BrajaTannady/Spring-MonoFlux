package com.example.SpringMonoFlux.controller;

import com.example.SpringMonoFlux.entity.ListTodo;
import com.example.SpringMonoFlux.service.api.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ListController {
  @Autowired
  ListService service;

  @CachePut(value = "todos", key = "#listTodo")
  @PostMapping("/insertList")
  public Mono<ListTodo> insertList(@RequestBody ListTodo listTodo) {
    System.out.println("Creating Record");
    return service.addListTodo(listTodo);
  }

  @Cacheable(value = "todos")
  @GetMapping("/findAllList")
  public Flux<ListTodo> findAllList() {
    System.out.println("Getting All Record");
    return service.findAllList();
  }

  @Cacheable(value = "todos", key = "#status")
  @GetMapping("/findAllListByStatus/{status}")
  public Flux<ListTodo> findAllListByStatus(@PathVariable boolean status) {
    System.out.println("Getting Record");
    return service.findAllListByStatus(status);
  }

  @CachePut(value = "todos", key = "#id")
  @PutMapping("/updateListName/{id}")
  public Mono<ListTodo> updateList(@PathVariable String id, @RequestBody ListTodo updatedList) {
    System.out.println("Updating Record");
    return service.updateList(id, updatedList);
  }

  @CachePut(value = "todos", key = "#id")
  @PutMapping("/updateCompleted/{id}")
  public Mono<ListTodo> updateListStatusToCompleted(@PathVariable String id) {
    System.out.println("Updating Record");
    return service.updateListStatusToCompleted(id);
  }

  @CachePut(value = "todos", key = "#id")
  @PutMapping("/updateActive/{id}")
  public Mono<ListTodo> updateListStatusToActive(@PathVariable String id) {
    System.out.println("Updating Record");
    return service.updateListStatusToActive(id);
  }

  @CachePut(value = "todos", key = "#status")
  @PutMapping("/updateAllStatus/{status}")
  public Flux<ListTodo> updateAllStatus(@PathVariable boolean status) {
    System.out.println("Updating Record");
    return service.updateAllStatus(status);
  }

  @CacheEvict(value = "todos", key = "#id")
  @DeleteMapping("/deleteList/{id}")
  public Mono<Void> deleteByListName(@PathVariable String id) {
    System.out.println("Delete Record");
    return service.deleteByListName(id);
  }

  @CacheEvict(value = "todos", allEntries = true)
  @DeleteMapping("/deleteAll")
  public Mono<Void> deleteAll() {
    System.out.println("Delete All Record");
    return service.deleteAll();
  }

}

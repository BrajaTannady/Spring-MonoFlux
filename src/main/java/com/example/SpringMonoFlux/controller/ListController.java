package com.example.SpringMonoFlux.controller;

import com.example.SpringMonoFlux.entity.ListTodo;
import com.example.SpringMonoFlux.service.api.ListService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/insertList")
    public Mono<ListTodo> insertList(@RequestBody ListTodo listTodo){
      return service.addListTodo(listTodo);
    }

    @GetMapping("/findAllList")
    public Flux<ListTodo> findAllList(){
      return service.findAllList();
    }
    @GetMapping("/findAllListByStatus/{status}")
    public Flux<ListTodo> findAllListByStatus(@PathVariable boolean status){
      return service.findAllListByStatus(status);
    }

    @PutMapping("/updateListName/{id}")
    public Mono<ListTodo> updateList(@PathVariable String id, @RequestBody ListTodo updatedList){
      return service.updateList(id, updatedList);
    }

    @PutMapping("/updateCompleted/{id}")
    public Mono<ListTodo> updateListStatusToCompleted(@PathVariable String id){
      return service.updateListStatusToCompleted(id);
    }

    @PutMapping("/updateActive/{listName}")
    public Mono<ListTodo> updateListStatusToActive(@PathVariable String listName){
      return service.updateListStatusToActive(listName);
    }

    @PutMapping("/updateAllStatus/{status}")
    public Flux<ListTodo> updateAllStatus(@PathVariable boolean status){
      return service.updateAllStatus(status);
    }

    @DeleteMapping("/deleteList/{id}")
    public Mono<Void> deleteByListName(@PathVariable String id){
      return service.deleteByListName(id);
    }

    @DeleteMapping("/deleteAll")
    public Mono<Void> deleteAll(){
      return service.deleteAll();
    }

}

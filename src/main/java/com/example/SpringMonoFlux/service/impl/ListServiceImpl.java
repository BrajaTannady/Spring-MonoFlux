package com.example.SpringMonoFlux.service.impl;
import com.example.SpringMonoFlux.entity.ListTodo;
import com.example.SpringMonoFlux.repository.ListRepository;
import com.example.SpringMonoFlux.service.api.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ListServiceImpl implements ListService {
  @Autowired
  ListRepository listRepository;

  @Override
  public Mono<ListTodo> addListTodo(ListTodo listTodo) {
    return listRepository.save(listTodo);
  }

  @Override
  public Flux<ListTodo> findAllList() {
    return listRepository.findAll();
  }

  @Override
  public Flux<ListTodo> findAllListByStatus(boolean status) {
    return listRepository.findAllByStatus(status);
  }

  @Override
  public Mono<ListTodo> updateList(String id, ListTodo updatedList) {
    return listRepository
        .findById(id)
        .flatMap(existingList -> {
          existingList.setListName(updatedList.getListName());
          existingList.setStatus(true);
          return listRepository.save(existingList);
        });
  }

  @Override
  public Mono<ListTodo> updateListStatusToCompleted(String id) {
    return listRepository
        .findById(id)
        .flatMap(existingList -> {
          existingList.setStatus(false);
          return listRepository.save(existingList);
        });
  }

  @Override
  public Mono<ListTodo> updateListStatusToActive(String id) {
    return listRepository
        .findById(id)
        .flatMap(existingList -> {
          existingList.setStatus(true);
          return listRepository.save(existingList);
        });
  }

  @Override
  public Mono<Void> deleteByListName(String id) {
    return listRepository.deleteById(id);
  }

  @Override
  public Mono<Void> deleteAll() {
    return listRepository.deleteAll();
  }

}

package com.example.SpringMonoFlux.repository;

import com.example.SpringMonoFlux.entity.ListTodo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Repository
public interface ListRepository extends ReactiveMongoRepository<ListTodo, String> {
  Flux<ListTodo> findAllByStatus(boolean status);
}

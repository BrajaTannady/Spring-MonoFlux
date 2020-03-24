package com.example.SpringMonoFlux.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.Generated;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "List")
public class ListTodo {
  @Id
  private String id;
  private String listName;
  private boolean status;
}

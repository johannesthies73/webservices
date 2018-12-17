package de.hszg.webservices.ts04.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoList {

    private String id;
    private String name;
    private Set<TodoListItem> items;

}

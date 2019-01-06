package de.hszg.webservices.ts05.todo;

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

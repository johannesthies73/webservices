package de.hszg.webservices.ts05.todo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoListItem {

    private String id;
    private String description;
    private boolean done;
}

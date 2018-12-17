package de.hszg.webservices.ts04.entity;

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

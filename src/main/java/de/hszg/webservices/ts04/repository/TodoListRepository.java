package de.hszg.webservices.ts04.repository;

import de.hszg.webservices.ts04.entity.TodoList;
import de.hszg.webservices.ts04.entity.TodoListItem;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
public class TodoListRepository implements Serializable {

    private Map<String, TodoList> lists;

    @PostConstruct
    public void init() {
        this.lists = new HashMap<String, TodoList>();
        lists.put("list1", new TodoList("list1", "myFirstTodoList", new HashSet<>(Arrays.asList(
                new TodoListItem("item11", "mydesc11", false),
                new TodoListItem("item12", "mydesc12", false),
                new TodoListItem("item13", "mydesc13", false),
                new TodoListItem("item14", "mydesc14", false)
        ))));

        lists.put("list2", new TodoList("list2", "mysecondTodoList", new HashSet<>(Arrays.asList(
                new TodoListItem("item21", "mydesc21", false),
                new TodoListItem("item22", "mydesc22", false),
                new TodoListItem("item23", "mydesc23", false),
                new TodoListItem("item24", "mydesc24", false)
        ))));
    }

    public List<TodoList> findAllLists() {
        return lists.values().stream().collect(Collectors.toList());
    }

    public Optional<TodoList> findList(String listId) {
        Optional<TodoList> list = Optional.of(lists.get(listId));

        return list;
    }

    public List<TodoListItem> findAllItems(String listId) {
        return lists.get(listId).getItems().stream().collect(Collectors.toList());
    }

    public Optional<TodoListItem> findItem(String listId, String itemId) {
        Optional<TodoList> todoList = findList(listId);

        if (todoList.isPresent()) {

            for (TodoListItem item : todoList.get().getItems()) {
                if (item.getId().equals(itemId)) {
                    return Optional.of(item);
                }
            }
        }

        return Optional.empty();
    }


    public Optional<TodoList> createTodoList(TodoList list) {
        String id = UUID.randomUUID().toString();
        lists.put(id, new TodoList(id, list.getName(), list.getItems()));
        return Optional.of(lists.get(id));
    }

    public Optional<TodoListItem> createItem(String listId, TodoListItem item) {
        Optional<TodoList> list = findList(listId);
        String newItemId = UUID.randomUUID().toString();
        item.setId(newItemId);

        if (list.isPresent()) {
            list.get().getItems().add(item);
            return findItem(listId, newItemId);
        }
        return findItem(listId, newItemId);
    }

    public Optional<TodoList> updateTodoList(String listId, TodoList list) {

        if (findList(listId).isPresent()) {
            lists.put(listId, new TodoList(listId, list.getName(), list.getItems()));
            return findList(listId);
        }

        return findList(listId);
    }

    public Optional<TodoListItem> updateItem(String listId, String itemId, TodoListItem item) {
        Optional<TodoList> list = findList(listId);

        if (list.isPresent()) {
            list.get().setItems(
                    list.get().getItems().stream().map(todoListItem -> {
                        if (todoListItem.getId().equals(itemId)) {
                            todoListItem = new TodoListItem(itemId, item.getDescription(), item.isDone());
                        }
                        return todoListItem;
                    }).collect(Collectors.toSet())
            );
            return findItem(listId, itemId);
        }

        return findItem(listId, itemId);
    }

    public Boolean removeTodoList(String listId) {
        if (findList(listId).isPresent()) {
            lists.remove(listId);
            return true;
        }

        return false;
    }

    public Boolean removeItem(String listId, String itemId) {
        Optional<TodoList> list = findList(listId);

        if (list.isPresent()) {
            list.get().setItems(list.get().getItems().stream().map(todoListItem -> {
                if (todoListItem.getId().equals(itemId)) {
                    todoListItem = null;
                }
                return todoListItem;
            }).filter(Objects::nonNull).collect(Collectors.toSet()));
            return true;
        }
        return false;
    }

//
//    public void markItemAsDone(TodoList list, TodoListItem item) {
//        lists.get(list.getId()).getItems().parallelStream().forEach(todoListItem -> {
//            if (todoListItem.getId().equals(item.getId())) {
//                todoListItem.setDone(true);
//            }
//        });
//    }
//
//    public TodoListItem addItem(TodoList list, String itemDescription) {
//        TodoListItem item = new TodoListItem(
//                UUID.randomUUID().toString(),
//                itemDescription,
//                false
//        );
//        lists.get(list.getId()).getItems().add(item);
//
//        return item;
//    }
//
//    public void markItemAsUndone(TodoList list, TodoListItem item) {
//        lists.get(list.getId()).getItems().parallelStream().forEach(todoListItem -> {
//            if (todoListItem.getId().equals(item.getId())) {
//                todoListItem.setDone(false);
//            }
//        });
//    }
//
//    private void setItemDoneStatusTo(TodoList list, TodoListItem item, boolean status) {
//        lists.get(list.getId()).getItems().stream().forEach(todoListItem -> {
//            if (item.getId() == todoListItem.getId()) {
//                todoListItem.setDone(status);
//            }
//        });
//
//    }
//

}

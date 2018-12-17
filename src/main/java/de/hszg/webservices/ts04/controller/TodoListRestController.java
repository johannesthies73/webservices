package de.hszg.webservices.ts04.controller;

import de.hszg.webservices.ts04.entity.TodoList;
import de.hszg.webservices.ts04.entity.TodoListItem;
import de.hszg.webservices.ts04.repository.TodoListRepository;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("lists")
public class TodoListRestController {

    @Inject
    private TodoListRepository listRepository;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllLists() {
        return Response.ok(this.listRepository.findAllLists()).build();
    }

    @GET
    @Path("/{listId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findListById(
            @NotNull @PathParam("listId") String listId
    ) {
        Optional<TodoList> list = this.listRepository.findList(listId);
        if (list.isPresent()) {
            return Response.ok(list.get()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/{listId}/items")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllItems(
            @NotNull @PathParam("listId") String listId
    ) {
        Optional<TodoList> list = this.listRepository.findList(listId);
        if (list.isPresent()) {
            return Response.ok(list.get().getItems()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/{listId}/items/{itemId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findItemById(
            @NotNull @PathParam("listId") String listId,
            @NotNull @PathParam("itemId") String itemId
    ) {
        Optional<TodoListItem> item = this.listRepository.findItem(listId, itemId);
        if (item.isPresent()) {
            return Response.ok(item.get()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }


    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createTodoList(
            @NotNull TodoList list
    ) {
        if (list.getId() != null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            Optional<TodoList> listOptional = this.listRepository.createTodoList(list);
            return Response
                    .status(Response.Status.CREATED)
                    .entity(listOptional.get())
                    .build();
        }
    }

    @POST
    @Path("/{listId}/items")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createItem(
            @NotNull @PathParam("listId") String listId,
            @NotNull TodoListItem item
    ) {
        if (item.getId() != null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            Optional<TodoListItem> responseBody = this.listRepository.createItem(listId, item);
            return Response
                    .status(Response.Status.CREATED)
                    .entity(responseBody.get())
                    .build();
        }
    }


    @PUT
    @Path("/{listId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTodoList(
            @NotNull @PathParam("listId") String listId,
            @NotNull TodoList newList
    ) {

        if (newList.getId() == null) {
            Optional<TodoList> responseBody = this.listRepository.updateTodoList(listId, newList);
            if (responseBody.isPresent()) {
                return Response
                        .status(202)
                        .entity(responseBody.get())
                        .build();
            }
        }
        return Response.status(Response.Status.BAD_REQUEST).build();

    }

    @PUT
    @Path("/{listId}/items/{itemId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response putTodoListItem(
            @NotNull @PathParam("listId") String listId,
            @NotNull @PathParam("itemId") String itemId,
            @NotNull TodoListItem newItem
    ) {

        if (newItem.getId() == null) {
            Optional<TodoListItem> responseBody = this.listRepository.updateItem(listId, itemId, newItem);
            if (responseBody.isPresent()) {
                return Response
                        .status(202)
                        .entity(responseBody.get())
                        .build();
            }
        }

        return Response.status(Response.Status.BAD_REQUEST).build();

    }

    @DELETE
    @Path("/{listId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTodoList(
            @NotNull @PathParam("listId") String listId
    ) {

        Boolean success = this.listRepository.removeTodoList(listId);

        if (success) {
            List<TodoList> responseBody = this.listRepository.findAllLists();
            return Response
                    .status(204)
                    .entity(responseBody)
                    .build();
        }

        return Response.status(Response.Status.BAD_REQUEST).build();

    }

    @DELETE
    @Path("/{listId}/items/{itemId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteItem(
            @NotNull @PathParam("listId") String listId,
            @NotNull @PathParam("itemId") String itemId
    ) {

        Boolean success = this.listRepository.removeItem(listId, itemId);

        if (success) {
            List<TodoListItem> responseBody = this.listRepository.findAllItems(listId);
            return Response
                    .status(204)
                    .entity(responseBody)
                    .build();
        }


        return Response.status(Response.Status.BAD_REQUEST).build();

    }

}

package de.hszg.webservices.ts04;

import de.hszg.webservices.ts04.controller.TodoListRestController;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        addRestRessourceClasses(resources);
        return resources;
    }

    private void addRestRessourceClasses(Set<Class<?>> resources) {
        resources.add(TodoListRestController.class);
    }

}

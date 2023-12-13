package com.sap.langer.edu.todoeduapplication.services.businessexceptions;

public class ToDoTaskNotFoundException extends RuntimeException {
    public ToDoTaskNotFoundException(final Long id)
    {
        super("Couldn't find ToDoTask with id: " + id);
    }
}

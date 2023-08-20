package org.skg.emsbackend.mapper;

import org.skg.emsbackend.entity.Todo;
import org.skg.emsbackend.dto.TodoDto;
public class ToDoDtoMapper
{
    public static TodoDto mapToToDoDto(Todo toDo)
    {
        return new TodoDto(
                toDo.getId(),
                toDo.getTitle(),
                toDo.getDescription(),
                toDo.isCompleted()
        );
    }

    public static Todo mapToToDoDto(TodoDto toDoDto)
    {
        return new Todo(
                toDoDto.getId(),
                toDoDto.getTitle(),
                toDoDto.getDescription(),
                toDoDto.isCompleted()
        );
    }
}

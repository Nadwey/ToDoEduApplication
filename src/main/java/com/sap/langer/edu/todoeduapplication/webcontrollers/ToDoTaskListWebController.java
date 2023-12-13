package com.sap.langer.edu.todoeduapplication.webcontrollers;

import com.sap.langer.edu.todoeduapplication.domain.ToDoStatus;
import com.sap.langer.edu.todoeduapplication.services.dtos.ToDoTaskDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.sap.langer.edu.todoeduapplication.services.ToDoListService;
import com.sap.langer.edu.todoeduapplication.services.dtos.ToDoListDTO;
import com.sap.langer.edu.todoeduapplication.webcontrollers.formdtos.NewToDoTask;
import com.sap.langer.edu.todoeduapplication.webcontrollers.responses.ToDoListResponse;
import lombok.extern.java.Log;
import java.util.List;

@Log
@Controller
@RequestMapping(value = "/html")
public class ToDoTaskListWebController {
	private final ToDoListService toDoListService;
	private final ModelMapper modelMapper;

	public ToDoTaskListWebController(final ToDoListService toDoListService, final ModelMapper modelMapper)
	{
		this.toDoListService = toDoListService;
		this.modelMapper = modelMapper;
	}

	@GetMapping("/todolist/{listId}")
    public String getToDoList(@PathVariable final Long listId, final Model model) {
        model.addAttribute("todoTaskList", findToDoList(listId));
		final NewToDoTask newToDoTask = new NewToDoTask();
		newToDoTask.setListId(listId);
		model.addAttribute("toDoTask", newToDoTask);
        return "tasklist";
    }

	@PostMapping("/todolist")
	public String addToDoTask(final Model model, @ModelAttribute final NewToDoTask toDoTask)
	{
		log.info(
				"New task: " + toDoTask.getTitle() + " " + toDoTask.getDescription() + " " + toDoTask.getScheduled() + " for list id " + toDoTask.getListId());
		toDoListService.addTaskToToDoList(toDoTask);
		return "redirect:todolist/" + toDoTask.getListId();
	}

   private ToDoListResponse findToDoList(final Long listId) {
	   ToDoListDTO toDoList = toDoListService.getToDoList(listId);
	   // There's probably a much better way to do this
	   List<ToDoTaskDTO> tasks = toDoList.getTasks();
	   tasks.sort((o1, o2) -> Boolean.compare(o2.getStatus() == ToDoStatus.IN_PROGRESS, o1.getStatus() == ToDoStatus.IN_PROGRESS));
	   toDoList.setTasks(tasks);
	   return convertToResponse(toDoList);
   }

	private ToDoListResponse convertToResponse(final ToDoListDTO toDoList)
	{
		return modelMapper.map(toDoList, ToDoListResponse.class);
	}
}

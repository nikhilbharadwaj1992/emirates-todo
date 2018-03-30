/**
 * 
 */
package com.emirates.todo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.emirates.todo.model.HttpPostResponse;
import com.emirates.todo.model.TaskStatus;
import com.emirates.todo.model.TodoModel;
import com.emirates.todo.services.TodoService;

/**
 * @author bharadsm
 *
 */

@RestController
@RequestMapping(value = "/todo")
public class TodoController {
	
	@Autowired
	TodoService service;

	@RequestMapping(value = "/list")
	public List<TodoModel> getTodoList() {
		return service.getTodoList();
	}
	
	@RequestMapping(value = "/tasks-by-status")
	public List<TodoModel> getTasksByStatus(@RequestParam(value = "status") TaskStatus status) {
		return service.getTasksByStatus(status);
	}
	
	@RequestMapping(value = "/add-task", method = RequestMethod.POST)
	public TodoModel addTask(@RequestBody TodoModel requestBody) {
		return service.addTask(requestBody);
	}
	
	@RequestMapping(value = "/delete-task", method = RequestMethod.POST)
	public HttpPostResponse deleteTask(@RequestBody TodoModel requestBody) {
		return service.deleteTask(requestBody);
	}
	
	@RequestMapping(value = "/toggle-status", method = RequestMethod.PUT)
	public HttpPostResponse toggleStatus(@RequestBody TodoModel requestBody) {
		return service.toggleStatus(requestBody);
	}
}

/**
 * 
 */
package com.emirates.todo.services;

import java.util.List;

import com.emirates.todo.model.HttpPostResponse;
import com.emirates.todo.model.TaskStatus;
import com.emirates.todo.model.TodoModel;

/**
 * @author bharadsm
 *
 */

public interface TodoService {

	public List<TodoModel> getTodoList();

	public List<TodoModel> getTasksByStatus(TaskStatus status);

	public TodoModel addTask(TodoModel requestBody);

	public HttpPostResponse deleteTask(TodoModel requestBody);

	public HttpPostResponse toggleStatus(TodoModel requestBody);
	
}

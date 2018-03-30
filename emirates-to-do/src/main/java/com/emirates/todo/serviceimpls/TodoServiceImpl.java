/**
 * 
 */
package com.emirates.todo.serviceimpls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.emirates.todo.model.HttpPostResponse;
import com.emirates.todo.model.TaskStatus;
import com.emirates.todo.model.TodoModel;
import com.emirates.todo.repositories.TodoRepository;
import com.emirates.todo.services.TodoService;

/**
 * @author bharadsm
 *
 */

@Service
public class TodoServiceImpl implements TodoService {
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Autowired
	TodoRepository repository;
	
	@Override
	public List<TodoModel> getTodoList() {
		return repository.findAll();
	}

	@Override
	public List<TodoModel> getTasksByStatus(TaskStatus status) {		
		return repository.getTasksByStatus(status);
	}

	@Override
	public TodoModel addTask(TodoModel requestBody) {
		return repository.save(requestBody);
	}

	@Override
	public HttpPostResponse deleteTask(TodoModel requestBody) {
		repository.deleteById(requestBody.getId());
		HttpPostResponse response = new HttpPostResponse();
		response.setStatus("SUCCESS");
		response.setMessage("Successfully deleted the task");		
		return response;
	}

	@Override
	public HttpPostResponse toggleStatus(TodoModel requestBody) {
		mongoTemplate.updateFirst(new Query(Criteria.where("_id").is(requestBody.getId())), new Update().set("status", requestBody.getStatus()), TodoModel.class);
		HttpPostResponse response = new HttpPostResponse();
		response.setStatus("SUCCESS");
		response.setMessage("Successfully deleted the task");		
		return response;
	}

}

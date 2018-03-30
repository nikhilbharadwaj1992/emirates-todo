/**
 * 
 */
package com.emirates.todo.repositories;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.emirates.todo.model.TaskStatus;
import com.emirates.todo.model.TodoModel;

/**
 * @author bharadsm
 *
 */

@Repository
public interface TodoRepository extends MongoRepository<TodoModel, ObjectId> {

	@Query("{'status':?0}")
	public List<TodoModel> getTasksByStatus(TaskStatus status);

}

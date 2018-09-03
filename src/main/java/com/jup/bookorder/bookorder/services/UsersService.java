package com.jup.bookorder.bookorder.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.jup.bookorder.bookorder.entities.User;
import com.jup.bookorder.bookorder.repositories.UsersRepo;

@Service
public class UsersService {

//	@Autowired
//	private UsersRepo todoRepo;
//
//	public User getTodoById(Long id){
//		User todo = todoRepo.findOne(id);
//		if(todo != null)
//			return todo;
//		else
//			throw new EntityNotFoundException("Todo["+id+"] Not Found");
//	}
//
//	public List<User> getTodoAll(){
//		return todoRepo.findAll();
//	}
//
//	public Page<User> getTodoPage(Integer number, Integer size){
//		return todoRepo.findAll(generatePageRequest(number, size));
//	}
//
//	public User saveTodo(User todo){
//		todo.setId(null);
//		return todoRepo.save(todo);
//	}
//
//	public User updateTodo(User todo){
//		if(todo.getId() == null) throw new EntityNotFoundException("Todo[] Not Found");
//		User qObj = todoRepo.findOne(todo.getId());
//		if(qObj != null){
//			qObj.setTitle(todo.getTitle());
//			qObj.setData(todo.getData());
//			qObj.setStatus(todo.getStatus());
//			return todoRepo.save(qObj);
//		}else{
//			throw new EntityNotFoundException("Todo["+todo.getId()+"] Not Found");
//		}
//	}
//
//	public User updateStatus(Long id, String status){
//		User qObj = todoRepo.findOne(id);
//		if(qObj != null){
//			qObj.setStatus(status);
//			return todoRepo.save(qObj);
//		}else{
//			throw new EntityNotFoundException("Todo["+id+"] Not Found");
//		}
//	}
//
//	public void deleteTodo(Long id){
//		User qObj = todoRepo.findOne(id);
//		if(qObj != null) {
//			todoRepo.delete(id);
//		}
//	}
//
//	public PageRequest generatePageRequest(Integer number, Integer size){
//		if(number == null) number = 0;
//		if(size == null) size = 99;
//		return new PageRequest(number, size);
//	}
}

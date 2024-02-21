package org.jsp.todo_app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.jsp.todo_app.dto.Task;
import org.jsp.todo_app.dto.User;

import com.mysql.cj.Query;

public class TodoDao 
{
	EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("m7");
	EntityManager entityManager=entityManagerFactory.createEntityManager();
	EntityTransaction entityTransaction=entityManager.getTransaction();
	
	
	public void saveuser(User user)
	{
		entityTransaction.begin();
		entityManager.persist(user);
		entityTransaction.commit();
	}
	
	public List<User> findByEmail(String email)
	
	{
		return entityManager.createQuery("select x from User x where email=?1").setParameter(1, email).getResultList();
	}
	
	public List<User> findByMobile(Long mobile)
	
	{
		return entityManager.createQuery("select x from User x where mobile=?1").setParameter(1, mobile).getResultList();
	}

	public void saveTask(Task task) 
	{
		entityTransaction.begin();
		entityManager.persist(task);
		entityTransaction.commit();
		
	}
	
	public List<Task> fetchTaskByUserId(int userId)
	{
		return entityManager.createQuery("select x from Task x where user_id=?1").setParameter(1, userId).getResultList();
	}
	
	public Task findTaskByid(int id)
	{
		return entityManager.find(Task.class, id);
	}
	
	public void updateTask(Task task)
	{
		entityTransaction.begin();
		entityManager.merge(task);
		entityTransaction.commit();
	}
	
	public void deleteTask(Task task)
	{
		entityTransaction.begin();
		entityManager.remove(task);
		entityTransaction.commit();
	}
	
}

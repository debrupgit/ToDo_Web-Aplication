package org.jsp.todo_app.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsp.todo_app.dto.User;
import org.jsp.todo_app.service.ToDoService;


@WebServlet("/signup")
public class SignupServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{

		ToDoService service=new ToDoService();
		service.signup(req, resp);
		
		
//		String name=req.getParameter("names");
//		String email=req.getParameter("emails");
//		String password=req.getParameter("pass");
//		Long mobile=Long.parseLong(req.getParameter("phns"));
//		String gender=req.getParameter("gender");
//		String dob=req.getParameter("dob");
		
//		resp.getWriter().print("<h1>"+name+"</h1>");
		
//		resp.getWriter().print("<h1>"+email+"</h1>");
//		resp.getWriter().print("<h1>"+phone+"</h1>");
//		resp.getWriter().print("<h1>"+password+"</h1>");
//		resp.getWriter().print("<h1>"+dob+"</h1>");
//		resp.getWriter().print("<h1>"+gender+"</h1>");
		
		
//		
//		User user=new User();
//		user.setDob(LocalDate.parse(dob));
//		user.setEmail(email);
//		user.setGender(gender);
//		user.setName(name);
//		user.setPassword(password);
//		user.setMobile(mobile);
//		
//		EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("m7");
//		EntityManager entityManager=entityManagerFactory.createEntityManager();
//		EntityTransaction entityTransaction=entityManager.getTransaction();
//		
//		entityTransaction.begin();
//		entityManager.persist(user);
//		entityTransaction.commit();
		
//		resp.getWriter().print("<h1>"+"Account Created Successfully"+"</h1>");
//		req.getRequestDispatcher("login.html").include(req, resp);
	}
}

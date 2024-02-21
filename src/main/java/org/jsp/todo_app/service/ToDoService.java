package org.jsp.todo_app.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.jsp.todo_app.dao.TodoDao;
import org.jsp.todo_app.dto.Task;
import org.jsp.todo_app.dto.User;

public class ToDoService 
{
	TodoDao dao = new TodoDao();
	
	public void signup(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException
	{
		//recieve from frontend
		User user=new User();
		user.setDob(LocalDate.parse(req.getParameter("dob")));
		user.setEmail(req.getParameter("emails"));
		user.setGender(req.getParameter("gender"));
		user.setName(req.getParameter("names"));
		user.setPassword(req.getParameter("pass"));
		user.setMobile(Long.parseLong(req.getParameter("phns")));
		
		
		//for checking duplicate
		List<User> Email=dao.findByEmail(user.getEmail());
		List <User> Mobile=dao.findByMobile(user.getMobile());
		
		
		if(Email.isEmpty() && Mobile.isEmpty())
		{
			dao.saveuser(user);
			resp.getWriter().print("<h1>"+"Account Created Successfully"+"</h1>");
			req.getRequestDispatcher("login.html").include(req, resp);
		}
		
		else
		{
			if(Email.isEmpty())
			{
				resp.getWriter().print("<h1>"+user.getMobile()+"Mobile already Exist"+"</h1>");
				req.getRequestDispatcher("login.html").include(req, resp);
			}
			else if(Mobile.isEmpty())
			{
				resp.getWriter().print("<h1>"+user.getEmail()+"Email already Exist"+"</h1>");
				req.getRequestDispatcher("login.html").include(req, resp);
				
			}
			
			else
			{
				resp.getWriter().print("<h1>"+"Mobile & Email already Exist"+"</h1>");
				req.getRequestDispatcher("login.html").include(req, resp);
			}
		}
		
	}
	
	public void logIn(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException
	{
		String emph=req.getParameter("emph");
		String password=req.getParameter("psw");
		List<User>list=null;
		
		try
		{
			long mobile=Long.parseLong(emph);
			list = dao.findByMobile(mobile);
			
			if (list.isEmpty())
			{
				resp.getWriter().print("<h1 align='center' style='color:red'>Incorrect Mobile Number</h1>");
			}
		}
		
		catch (NumberFormatException e) 
		{
			String email = emph;
			list = dao.findByEmail(email);
			if (list.isEmpty())
			{
				resp.getWriter().print("<h1 align='center' style='color:red'>Incorrect Email</h1>");
			}
		}
		
		if (!list.isEmpty()) 
		{
			User user=list.get(0);
			if (user.getPassword().equals(password))
			{
				req.getSession().setAttribute("user", user);
				resp.getWriter().print("<h1 align='center' style='color:green'>Login Success</h1>");
				
				List<Task> tasks=dao.fetchTaskByUserId(user.getId());
				req.setAttribute("tasks",tasks);
				
				req.getRequestDispatcher("home.jsp").include(req, resp);
			} 
			else 
			{
				resp.getWriter().print("<h1 align='center' style='color:red'>Incorrect Password</h1>");
				req.getRequestDispatcher("login.html").include(req, resp);
			}
		} 
		
		else 
		{
			req.getRequestDispatcher("login.html").include(req, resp);
		}
		
		
	}
	
	
	
	public void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException
	{
		req.getSession().removeAttribute("user");
		resp.getWriter().print("<h1>Logout Success</h1>");
		req.getRequestDispatcher("login.html").include(req, resp);
		req.getRequestedSessionId();
	}

	public void addTask(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException 
	{
		String name=req.getParameter("taskname");
		String description=req.getParameter("description");
		Part image=req.getPart("taskimage");
		
		Task task=new Task();
		task.setName(name);
		task.setDescription(description);
		task.setStatus(false);
		task.setAddedTime(LocalDateTime.now());
		
		byte[] pic=new byte[image.getInputStream().available()];
		image.getInputStream().read(pic);
		task.setImage(pic);
		
		User user=(User)req.getSession().getAttribute("user");
		task.setUser(user);
		
		dao.saveTask(task);
		
		resp.getWriter().print("<h1>Task added Success</h1>");
		
		List<Task> tasks=dao.fetchTaskByUserId(user.getId());
		req.setAttribute("tasks",tasks);
		
		req.getRequestDispatcher("home.jsp").include(req, resp);
	}
	
	public void completeTask(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException
	{
		int id=Integer.parseInt(req.getParameter("id"));
		Task task=dao.findTaskByid(id);
		task.setStatus(true);
		dao.updateTask(task);
		
		resp.getWriter().print("<h1>status changed Success</h1>");
		
		User user=(User)req.getSession().getAttribute("user");
		
		List<Task> tasks=dao.fetchTaskByUserId(user.getId());
		req.setAttribute("tasks",tasks);
		
		req.getRequestDispatcher("home.jsp").include(req, resp);
		
	}
	
	
	public void deleteTask(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException
	{
		int id=Integer.parseInt(req.getParameter("id"));
		Task task=dao.findTaskByid(id);
		dao.deleteTask(task);
		
		resp.getWriter().print("<h1>status changed Success</h1>");
		
		User user=(User)req.getSession().getAttribute("user");
		
		List<Task> tasks=dao.fetchTaskByUserId(user.getId());
		req.setAttribute("tasks",tasks);
		
		req.getRequestDispatcher("home.jsp").include(req, resp);
		
	}
	
	public void updateTask(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException
	{
		String name=req.getParameter("taskname");
		String description=req.getParameter("description");
		Part image=req.getPart("taskimage");
		int id=Integer.parseInt(req.getParameter("id"));
		
		Task task=new Task();
		task.setId(id);
		task.setName(name);
		task.setDescription(description);
		task.setStatus(false);
		task.setAddedTime(LocalDateTime.now());
		
		byte[] pic=new byte[image.getInputStream().available()];
		image.getInputStream().read(pic);
		
		if(pic.length==0)
		{
			task.setImage(dao.findTaskByid(id).getImage());
		}
		else
		{
			task.setImage(pic);
		}
		
		User user=(User)req.getSession().getAttribute("user");
		task.setUser(user);
		
		dao.updateTask(task);
		
		resp.getWriter().print("<h1>Task updated Success</h1>");
		
		List<Task> tasks=dao.fetchTaskByUserId(user.getId());
		req.setAttribute("tasks",tasks);
		
		req.getRequestDispatcher("home.jsp").include(req, resp);
	}
}

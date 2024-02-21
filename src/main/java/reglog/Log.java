package reglog;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/logs")
public class Log extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String email=req.getParameter("emp");
		String password=req.getParameter("psw");
		
		resp.getWriter().print("<h1>"+email+"</h1>");
		resp.getWriter().print("<h1>"+password+"</h1>");
	}
}

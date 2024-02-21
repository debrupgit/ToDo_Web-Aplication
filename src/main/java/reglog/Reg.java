package reglog;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/regs")
public class Reg extends HttpServlet 
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String name=req.getParameter("names");
		String email=req.getParameter("emails");
		String password=req.getParameter("pass");
		String phone=req.getParameter("phns");
		
		resp.getWriter().print("<h1>"+name+"</h1>");
		resp.getWriter().print("<h1>"+email+"</h1>");
		resp.getWriter().print("<h1>"+password+"</h1>");
		resp.getWriter().print("<h1>"+phone+"</h1>");
		
	}
}

package nomster;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class Oauth2callbackServlet extends HttpServlet 
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException 
	{
		PrintWriter out = resp.getWriter();
	    resp.setContentType("text/plain");
	    out.println("get");
	    out.println(req.getSession().getId());
	    if(req.getParameter("state")!=null)
	    {	   	
	    	if(req.getParameter("state").equals(req.getSession().getId()))
		    {
		    	out.println("valid login from session");		    	
		    }
		    else
		    {
		    	out.println("invalid session id");
		    }
	    }	
	    else
	    {
	    	out.println("missing state");
	    }
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException 
	{
		PrintWriter out = resp.getWriter();
	    resp.setContentType("text/plain");
	    out.println("post");
	}

}

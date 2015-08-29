package java2.ateam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SubmitDefect extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public SubmitDefect() {

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String relativeWebPath = "/WEB-INF/repository/";
		String dbPath = getServletContext().getRealPath(relativeWebPath);
		
		DatabaseAccess db = new DatabaseAccess(dbPath);
	    db.getConnection();
	    
		//if an id exists already, this is an update
		if (request.getParameter("id") != null){
			
		   //update this defect in the database
		    Integer rowsAffected = db.updateDefect(request.getParameter("title"), 
		    		request.getParameter("status"), 
		    		Integer.parseInt(request.getParameter("priority")), 
		    		Integer.parseInt(request.getParameter("assignee")), 
		    		request.getParameter("description"), 
		    		Integer.parseInt(request.getParameter("id")));
		    
			if (rowsAffected.equals(1)){
				
				request.setAttribute("message", "update_success");
			}
			else{
				request.setAttribute("message",  "update_failure");
		    	request.setAttribute("id", request.getParameter("id"));
		
			}
		}
		
		//this is a new record
		else{
			
			//create a new defect record in the database
			Integer rowsInserted = db.insertDefect(request.getParameter("title"), 
		    		request.getParameter("status"), 
		    		Integer.parseInt(request.getParameter("priority")), 
		    		Integer.parseInt(request.getParameter("assignee")), 
		    		request.getParameter("description"));
		    
			
		    
			if (rowsInserted.equals(1))
				request.setAttribute("message", "insert_success");
			else
				request.setAttribute("message", "insert_failure");

		}
		

		//close db connection
		db.closeConnection();
			
		request.getRequestDispatcher("ViewAll").forward(request, response);
	}

}

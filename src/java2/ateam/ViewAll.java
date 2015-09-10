package java2.ateam;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java2.ateam.Defect;

/**
 * Servlet implementation class ViewAll
 */
@WebServlet("/ViewAll")
public class ViewAll extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<User> userList;
	ArrayList<Priority> priorityList;
	ArrayList<Status> statusList;
	
	public ViewAll(){				
	}	
	
	 private void setDropdowns(String dbPath){
	    	
			DatabaseAccess db = new DatabaseAccess(dbPath);
			
			//get connection to database
	        db.getConnection();
			
			userList = db.getUsers();
			Collections.sort(userList);
	    		
		    if (priorityList == null || statusList == null){
		    	    priorityList = db.getPriorities();
		    	    statusList = db.getStatuses();
		    }
	    	    
			//close connection to database
			db.closeConnection();
	    	
	    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String relativeWebPath = "/WEB-INF/repository/";
		String dbPath = getServletContext().getRealPath(relativeWebPath);
		
		setDropdowns(dbPath);
		
		DatabaseAccess db = new DatabaseAccess(dbPath);
	    db.getConnection();
	    ArrayList<Defect> defectList = db.getDefects();	    
	    db.closeConnection();
	    
		request.setAttribute("defectList", defectList);
		request.setAttribute("priorityList", priorityList);
		request.setAttribute("userList", userList);
		request.setAttribute("statusList", statusList);
		
		//if request came from EditDefect, then pass along the message that a defect was updated or created.
		if (request.getAttribute("message") != null){
			request.setAttribute("message", request.getAttribute("message"));
		}
		request.getRequestDispatcher("/jsp/ViewAll.jsp").forward(request, response);
	}
	



}

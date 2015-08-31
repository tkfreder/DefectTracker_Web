package java2.ateam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditDefect extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ArrayList<User> userList;
	ArrayList<Priority> priorityList;
	ArrayList<Status> statusList;
	int mDefectId;
       
    public EditDefect() {
    }

    private void setDropdowns(String dbPath){
    	if (userList == null || priorityList == null || statusList == null){
    		DatabaseAccess db = new DatabaseAccess(dbPath);
    		
    		//get connection to database
	        db.getConnection();
    		
    		userList = db.getUsers();
    		Collections.sort(userList);
    		
    	    priorityList = db.getPriorities();
    	    statusList = db.getStatuses();
    	    
    		//close connection to database
    		db.closeConnection();
    	}
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	    
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String relativeWebPath = "/WEB-INF/repository/";
		String dbPath = getServletContext().getRealPath(relativeWebPath);
		
		setDropdowns(dbPath);
		
		//save defect id if exists in URL parameter
	    if (request.getParameter("id") != null){
	    	
	    	DatabaseAccess db = new DatabaseAccess(dbPath);
			
			//get connection to database
	        db.getConnection();
	        
	    	mDefectId = Integer.parseInt(request.getParameter("id"));
	    	
	    	//get defect details from database
		    Defect defect = db.getDefect(mDefectId);
		    
		  //close connection to database
		    db.closeConnection();
		    
		    request.setAttribute("defect", defect);	    	
	    }

		RequestDispatcher dispatcher = getServletConfig().getServletContext().getRequestDispatcher("/jsp/EditDefect.jsp");
		request.setAttribute("priorityList", priorityList);
		request.setAttribute("userList", userList);
		request.setAttribute("statusList", statusList);
		dispatcher.forward(request, response);
	}
}

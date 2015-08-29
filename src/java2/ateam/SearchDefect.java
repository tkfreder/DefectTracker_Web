package java2.ateam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchDefect
 */
@WebServlet("/SearchDefect")
public class SearchDefect extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ArrayList<User> userList;
	ArrayList<Priority> priorityList;
	ArrayList<Status> statusList;
	
	
       
    public SearchDefect() {
    }
    
    private void setDropdowns(String dbPath){
    	if (userList == null || priorityList == null || statusList == null){
    		DatabaseAccess db = new DatabaseAccess(dbPath);
    		userList = new ArrayList<User>();    	
    		priorityList = new ArrayList<Priority>();    	
    		statusList = new ArrayList<Status>();
    		
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
		
		String mStatusCode = "";
		int mPriorityId = -1;
		int mUserId = -1;
		
		//get dropdown values
		setDropdowns(dbPath);
		
		DatabaseAccess db = new DatabaseAccess(dbPath);
	    db.getConnection();
	    
	    
	    ArrayList<Defect> defectList;
	    
	    //build url 
	  	String url_ViewAll = "/jsp/ViewAll.jsp?";
	  	
	  	
	    //if there are search parameters
	    if (request.getParameter("status") != null || request.getParameter("priority") != null || request.getParameter("assignee") != null){
	    	
	    	//get parameters
		    
		    if (request.getParameter("status") != null){
		    	
		    	if (request.getParameter("status") != "") { 
		    		
		    		mStatusCode = request.getParameter("status");
		    		//pass status value back to form
			    	url_ViewAll += "status=" + request.getParameter("status");
			    	
		    	}
		    }
		    
		    if (request.getParameter("priority") != null){
		    	
		    	if (request.getParameter("priority") != "") {
		    		
		    		mPriorityId = Integer.parseInt(request.getParameter("priority"));
		    		
		    		//pass priority value back to form
			    	url_ViewAll += "&priority=" + request.getParameter("priority");  		
		    	}
		    }
		    
		    if (request.getParameter("assignee") != null){
		    	
		    	if (request.getParameter("assignee") != "") { 
		    		
		    		mUserId = Integer.parseInt(request.getParameter("assignee"));
		    		
		    		//pass assignee value back to form
			    	url_ViewAll += "&assignee=" + request.getParameter("assignee");
		    		
		    	}
		    }
		    
		    //get defect records based on search parameters
		    defectList = db.getDefectsByParams(mStatusCode, mPriorityId, mUserId);	
		
		//get all defect records
	    } else {
	    	
	    	defectList = db.getDefects();	
	    
	    }
 
	        
	    db.closeConnection();
	    
		request.setAttribute("defectList", defectList);
		request.setAttribute("priorityList", priorityList);
		request.setAttribute("userList", userList);
		request.setAttribute("statusList", statusList);
		
		//if request came from EditDefect, then pass along the message that a defect was updated or created.
		if (request.getAttribute("message") != null){
			request.setAttribute("message", request.getAttribute("message"));
		}
				
		//request.getRequestDispatcher("/jsp/ViewAll.jsp").forward(request, response);
		request.getRequestDispatcher(url_ViewAll).forward(request, response);
	}

}

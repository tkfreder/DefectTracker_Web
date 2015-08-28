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
	
	String mStatusCode;
	int mPriorityId = -1;
	int mUserId = -1;
       
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
		
		setDropdowns(dbPath);
		
		DatabaseAccess db = new DatabaseAccess(dbPath);
	    db.getConnection();
	    
	    //get parameters
	    
	    if (request.getParameter("status") != ""){
	    	
	    	mStatusCode = request.getParameter("status");
	    	
	    	//pass status value back to form
	    	request.setAttribute("status", request.getParameter("status"));
	    }
	    
	    if (request.getParameter("priority") != ""){
	    	
	    	mPriorityId = Integer.parseInt(request.getParameter("priority"));
	    	
	    	//pass priority value back to form
	    	request.setAttribute("priority", request.getParameter("priority"));
	    }
	    
	    if (request.getParameter("assignee") != ""){
	    	
	    	mUserId = Integer.parseInt(request.getParameter("assignee"));
	    	
	    	//pass assignee value back to form
	    	request.setAttribute("assignee", request.getParameter("assignee"));
	    }
	    
	    
	    ArrayList<Defect> defectList = db.getDefectsByParams(mStatusCode, mPriorityId, mUserId);	    
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

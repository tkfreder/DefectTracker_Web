package java2.ateam;


import java.io.IOException;
import java.util.ArrayList;
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
	
	public ViewAll(){				
	}	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String relativeWebPath = "/WEB-INF/repository/";
		String dbPath = getServletContext().getRealPath(relativeWebPath);
		
		DatabaseAccess db = new DatabaseAccess(dbPath);
	    db.getConnection();
	    ArrayList<Defect> defectList = db.getDefects();	    
	    db.closeConnection();
	    
		request.setAttribute("defectList", defectList);
		
		//if request came from EditDefect, then pass along the message that a defect was updated or created.
		if (request.getAttribute("message") != null){
			request.setAttribute("message", request.getAttribute("message"));
		}
		request.getRequestDispatcher("/jsp/ViewAll.jsp").forward(request, response);
	}
	



}

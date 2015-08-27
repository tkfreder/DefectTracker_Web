package java2.ateam;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SendEmail
 */
@WebServlet("/SendEmail")
public class SendEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public SendEmail() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String relativeWebPath = "/WEB-INF/repository/";
		String dbPath = getServletContext().getRealPath(relativeWebPath);
		
		int defectId = -1;
		
		defectId = (Integer) Integer.parseInt(request.getQueryString().substring(3, request.getQueryString().length()));
		
		
		DatabaseAccess db = new DatabaseAccess(dbPath);
	    db.getConnection();
	    ArrayList<User> userList = db.getUsers();
	    Defect defect = db.getDefect(defectId);
	    db.closeConnection();
	    
		request.setAttribute("defect", defect);
		request.setAttribute("userList", userList);
		
		request.getRequestDispatcher("/jsp/EmailDialog.jsp").forward(request, response);
	}

}

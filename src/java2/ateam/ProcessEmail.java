package java2.ateam;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(description = "Builds and sends email", urlPatterns = { "/ProcessEmail" })
public class ProcessEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public ProcessEmail() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// TODO: get request form data and build an email
		
		
		// TODO: implement email sending 
		
		
		/* TODO: implement or see if someone else wants to implement a message at the bottom of ViewAll that will 
		 * display whatever is passed to it
		 */
		
		//request.setAttribute("message", "email_success");
		request.setAttribute("message", "email_failure");
		
		request.getRequestDispatcher("ViewAll").forward(request, response);
		
		
	}

}

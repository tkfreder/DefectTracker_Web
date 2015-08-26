package java2.ateam;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProcessEmail
 */
@WebServlet(description = "Builds and sends email", urlPatterns = { "/ProcessEmail" })
public class ProcessEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public ProcessEmail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String emailSubject = "";
		String emailBody = "";
		String emailRecipients = "";
		ArrayList<String> emailAddresses;
		
		if (request.getParameter("emailSubject") != null || 
				request.getParameter("emailSubject") != null || 
				request.getParameter("emailSubject") != null)
		{
			
		
			emailSubject = (String)request.getParameter("emailSubject");
			emailBody = (String)request.getParameter("emailBody");
			emailRecipients = (String) request.getParameter("selectedEmailAddresses");
			
			emailAddresses = new ArrayList<String>();
			
			String mailList[] = emailRecipients.split(";");
			
			for (String email: mailList){
				emailAddresses.add(email);
			}
			
			
			String message = MailServer.sendEmail(emailAddresses, emailBody, emailSubject);
			
			if (message.contains("success")){
				request.setAttribute("message", "email_success");
			} else {
				request.setAttribute("message", "email_failure");
			}
			
					
		} else {
			request.setAttribute("message", "email_failure");	
		}
		
		
		request.getRequestDispatcher("ViewAll").forward(request, response);
		
	}

}

package java2.ateam;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/*  MailServer adapted by Tyrone Gubler from the example code found here: 
 *  http://crunchify.com/java-mailapi-example-send-an-email-via-gmail-smtp/
 */

public class MailServer {

	private static Properties mailServerProperties;
	private static Session getMailSession;
	private static MimeMessage mimeMailMessage;
	
	private static ArrayList<String> mRecipientList;
	private static String mEmailBody;
	private static String mEmailSubject;
	
	
 
	public static String sendEmail(ArrayList<String> recipientList, String emailBody, String emailSubject) {
		
		String returnMessage = "";
		mRecipientList = recipientList;
		mEmailBody = emailBody;
		mEmailSubject = emailSubject;
		
		try {
			generateAndSendEmail();
			returnMessage = "success";
		} 
		catch (AddressException e){
			e.printStackTrace();
			returnMessage = "error: an Address Exception occurred";
		}
		catch (MessagingException e) {
			e.printStackTrace();
			returnMessage = "error: a Messaging Exception occurred";
		} 
		
		return returnMessage;
	}
 
	private static void generateAndSendEmail() throws AddressException, MessagingException {
 
		// Step1
		//setup Mail Server Properties..
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
 
		// Step2
		//get Mail Session
		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		mimeMailMessage = new MimeMessage(getMailSession);
		
		// set to and CC
		for (String recipient: mRecipientList){
		mimeMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
		}
		
		// set subject line
		mimeMailMessage.setSubject(mEmailSubject);
		
		// set email body
		mimeMailMessage.setContent(mEmailBody, "text/html");
 
		// Step3
		// Get Session and Send mail
		Transport transport = getMailSession.getTransport("smtp");
 
		// Enter your correct gmail UserID and Password
		// if you have 2FA enabled then provide App Specific Password
		transport.connect("smtp.gmail.com", "better.bot@gmail.com", "Java2TeamArocks");
		transport.sendMessage(mimeMailMessage, mimeMailMessage.getAllRecipients());
		transport.close();
	}
	
}

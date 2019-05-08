package nano.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class HelperSendEmail {
	
	
	
	@Autowired
    private MailSender mailSender;
	
	
	public void sendEmailOrder(String email, long orderNo, String status) {
        System.out.println("Sending...");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("nxan1811@gmail.com");
        message.setTo(email);
        message.setSubject("FlowerShop");
        message.setText("Your order #" + orderNo + " is " + status);
        mailSender.send(message);
        System.out.println("Sending text done!");
       
	}
	
	
     
//    @Autowired
//    private SimpleMailMessage preConfiguredMessage;
    
    public void sendMail(String to, String subject, String body)
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
    
}

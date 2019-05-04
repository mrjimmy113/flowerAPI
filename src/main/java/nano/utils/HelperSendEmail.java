package nano.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import nano.repository.AccountRepository;

@Service
public class HelperSendEmail {
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	JavaMailSenderImpl smtp;
	
	@Autowired
	AccountRepository cl;
	
	
	public void sendEmailOrder(String email, long orderNo) {
		System.out.println(cl.getOne(1).getUsername());
		System.out.println("SMTP:" + cl);
        MailSender mailSender = (MailSender) applicationContext.getBean("smtp");
        System.out.println("Sending...");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("nxan1811@gmail.com");
        message.setTo(email);
        message.setSubject("FlowerShop");
        message.setText("Your order #" + orderNo);
        mailSender.send(message);
        System.out.println("Sending text done!");
       
	}
	
	@Autowired
    private MailSender mailSender;
     
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

package nano.utils;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class HelperSendEmail {
	
	public void sendEmailOrder(String email, long orderNo) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/WEB-INF/applicationContext.xml");
        MailSender mailSender = (MailSender) context.getBean("smtp");
        System.out.println("Sending...");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("nxan1811@gmail.com");
        message.setTo(email);
        message.setSubject("FlowerShop");
        message.setText("Your order #" + orderNo);
        mailSender.send(message);
        System.out.println("Sending text done!");
        context.close();
	}
}

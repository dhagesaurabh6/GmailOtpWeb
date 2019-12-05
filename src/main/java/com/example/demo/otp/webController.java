package com.example.demo.otp;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sun.xml.fastinfoset.sax.Properties;

import ch.qos.logback.classic.Logger;



@Controller
public class webController {
	
	@Autowired
	otpRepository otpRepository;
	
	//random otp generator
	private static String generateOTP() {
	      String numbers = "1234567890";
	      Random random = new Random();
	      char[] cotp = new char[6];
	      String otp ="";
	      for(int i = 0; i< 6 ; i++) {
	         cotp[i] = numbers.charAt(random.nextInt(numbers.length()));
	         otp =otp+cotp[i];
	      }
	      return otp;
	   }
	//url generator
	private  String generateUrl() {
		
		String resource_id = "i-8924534554";
		String user_id = "218441116";
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Instant instant = timestamp.toInstant();
		
		OtpValidationEntity otpValidationEntity = new OtpValidationEntity();

		
		otpValidationEntity.setStatus("not_verified");
		otpValidationEntity.setUid("218441116");
		otpValidationEntity.setAction("pending");
		otpValidationEntity.setResource_id("i-8924534554");
		
		OtpValidationEntity ent=otpRepository.save(otpValidationEntity);
		int alert_id=ent.getAlert_id();
		
		String url = "http://localhost:8080/validate?uid="+user_id+"&alert_id="+alert_id+"&resid="+resource_id+"&timestamp="+instant.toString()+"";
		return url;
	}
	
	public static void send(String from,String password,String to,String sub,String msg){  
        //Get properties object    
        java.util.Properties props = new java.util.Properties();    
        props.put("mail.smtp.host", "smtp.gmail.com");    
        props.put("mail.smtp.socketFactory.port", "465");    
        props.put("mail.smtp.socketFactory.class",    
                  "javax.net.ssl.SSLSocketFactory");    
        props.put("mail.smtp.auth", "true");    
        props.put("mail.smtp.port", "465");    
        //get Session   
        Session session = Session.getDefaultInstance(props,    
         new javax.mail.Authenticator() {    
         protected PasswordAuthentication getPasswordAuthentication() {    
         return new PasswordAuthentication(from,password);  
         }    
        });    
        //compose message    
        try {    
         MimeMessage message = new MimeMessage(session);    
         message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
         message.setSubject(sub);    
         message.setText(msg);    
         //send message  
         Transport.send(message);    
         System.out.println("otp sent successfully");    
        } catch (MessagingException e) {throw new RuntimeException(e);}    
           
  }  
	

	
	@RequestMapping("/generateALertData")
	public String process() throws Exception {
		
		
		String url = generateUrl();
		
		String receiver_mail = "dhagesaurabh3@gmail.com";
		String sender_mail ="saurabh.dhage2704@gmail.com";
		String sender_pass ="saurabh1111";
		String mail_message ="Go to below link to take action: \n\n"+url+"";
		String mail_subject="Cloud Resource Alert, action Required!!!!";
		
 
		send(sender_mail,sender_pass,receiver_mail,mail_subject,mail_message); 
		
		return "sucess";
	}
	
	@RequestMapping("/validate")
	public String proce() throws Exception {
		
		return "otpValidate.jsp";
	}
	
	
	
	@RequestMapping("/sendOTP")
	@ResponseStatus(value = HttpStatus.OK)
	public void sendOTP(@RequestParam("alert_id") String alert_id) {
		
		String otp1 =generateOTP();
		System.out.println(alert_id);
		Optional<OtpValidationEntity> otpValidationEntityUpdate = otpRepository.findById(Integer.parseInt( alert_id)); 
		 otpValidationEntityUpdate.get().setOtp(otp1);
		 otpRepository.save(otpValidationEntityUpdate.get());
		String receiver_mail = "dhagesaurabh3@gmail.com";
		String sender_mail ="saurabh.dhage2704@gmail.com";
		String sender_pass ="saurabh1111";
		String mail_message ="The otp for authentication: \n\n"+otp1+"";
		String mail_subject="OTP Authentication";

		  
		send(sender_mail,sender_pass,receiver_mail,mail_subject,mail_message);  

		
	}
	
	
	
	
	@RequestMapping("/otpValidate")
	public String otpValidate(@RequestParam("otp") String otp,@RequestParam("uid") String uid,@RequestParam("action") String action,@RequestParam("alert_id") Integer alert_id) 
	{
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();

	     Optional<OtpValidationEntity> otpValidationEntity = otpRepository.findById(alert_id);
	    
	     System.out.println("match ----------------------------------------------------------------------------------------");
	     System.out.println(otpValidationEntity.get().getOtp());
	     System.out.println(otp);
	     System.out.println(otpValidationEntity.get().getAlert_id());
	     System.out.println(alert_id);
	     
	    
	     
	     System.out.println("match ----------------------------------------------------------------------------------------");

	    
	    	 if(otp.contentEquals(otpValidationEntity.get().getOtp())&& alert_id.compareTo(otpValidationEntity.get().getAlert_id())==0)   	 
	    	 {
	    		 
	    		 System.out.println(otpValidationEntity.get().getStatus());
	    		 
	    		 if(otpValidationEntity.get().getStatus().contentEquals("not_verified")) 
	    		 {
		    		 Optional<OtpValidationEntity> otpValidationEntityUpdate = otpRepository.findById(alert_id); 
		    		 otpValidationEntityUpdate.get().setStatus("ACTION_TAKEN");
		    		 otpValidationEntityUpdate.get().setAction(action);
		    		 otpValidationEntityUpdate.get().setUid(uid);
		    		 otpRepository.save(otpValidationEntityUpdate.get());
		    		 return "SuccesPage.jsp";
	    		 }
	    		 
	    		 else {
	    			
	    			 return "errorUpdated.jsp";
	    			 
	    		 }
	    	 }
	    	
	    	 else
	    	 {
	    		 return "errorOtp.jsp";
	    	 }
	    	 
	
	}
	
	@RequestMapping("/generateAlert")
	public String generateAlert() {
		
		
		return "generateAlert.jsp";
	}
	
	@RequestMapping("/alertGenerated")
	public String alertGenerated() {
		
		
		return "alertGenerated.jsp";
	}

}

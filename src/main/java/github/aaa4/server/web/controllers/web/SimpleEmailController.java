package github.aaa4.server.web.controllers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SimpleEmailController {



    @Autowired
    public JavaMailSender emailSender;

    @ResponseBody
    @GetMapping("/admin/sendSimpleEmail")
    public String sendSimpleEmail(){
        SimpleMailMessage message  = new SimpleMailMessage();
        message.setTo("a.j.lapin1@gmail.com");
        message.setSubject("Registration message");
        message.setText("Hello, I'm testing simple java email.");
        emailSender.send(message);
        return "Email sent";
    }

}

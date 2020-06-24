package github.aaa4.server.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller("/dashboard")
public class DashboardController {


    @ResponseBody
    @GetMapping("/admin")
    public String getAdminDashboard(){
        return "Hello admin";
    }


}

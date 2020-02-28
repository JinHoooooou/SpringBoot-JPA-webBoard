package net.slipp.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
  @GetMapping("/hello")
  public String helloWorldPage(String userName, int userAge, Model model){
    System.out.println("userName : " + userName + " and userAge : " + userAge);
    model.addAttribute("userName", userName);
    model.addAttribute("userAge", userAge);
    return "welcome";
  }

}

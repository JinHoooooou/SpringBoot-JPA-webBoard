package net.slipp.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
  @PostMapping("/signUpUser")
  public String signUpUser(UserDto user, Model model){
    System.out.println(user);
    return "signUpUser";
  }
}

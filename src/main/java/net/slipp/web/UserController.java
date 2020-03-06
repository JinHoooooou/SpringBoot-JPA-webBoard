package net.slipp.web;

import java.util.ArrayList;
import java.util.List;
import net.slipp.domain.User;
import net.slipp.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

  @Autowired
  UserRepository userRepository;

  @PostMapping("/signUpUser")
  public String signUpUser(User user, Model model){
    System.out.println(user);
    userRepository.save(user);
    return "redirect:/userList";
  }

  @GetMapping("/userList")
  public String printUserList(Model model){
    model.addAttribute("userList", userRepository.findAll());
    return "printUserList";
  }
}

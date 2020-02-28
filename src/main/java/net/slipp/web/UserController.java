package net.slipp.web;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
  List<UserDto> userList = new ArrayList<>();

  @PostMapping("/signUpUser")
  public String signUpUser(UserDto user, Model model){
    System.out.println(user);
    userList.add(user);
    return "redirect:/userList";
  }

  @GetMapping("/userList")
  public String printUserList(Model model){
    model.addAttribute("userList", userList);
    return "printUserList";
  }
}

package net.slipp.web;

import net.slipp.domain.User;
import net.slipp.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("users")
public class UserController {

  @Autowired
  UserRepository userRepository;

  @GetMapping("signUp")
  public String goToSignUpFormPage() {
    return "user/signUpForm";
  }

  @GetMapping("{id}/update")
  public String goToUpdateFormPage(@PathVariable Long id, Model model) {
    model.addAttribute("user", userRepository.findById(id).get());
    return "user/updateForm";
  }

  @GetMapping("")
  public String goToPrintUserListPage(Model model){
    model.addAttribute("userList", userRepository.findAll());
    return "user/userList";
  }

  @GetMapping("loginForm")
  public String goToLoginFormPage() {
    return "user/loginForm";
  }

  @PostMapping("")
  public String signUpUser(User user, Model model){
    System.out.println(user);
    userRepository.save(user);
    return "redirect:/users";
  }

  @PutMapping("{id}/update")
  public String updateUser(@PathVariable Long id, User updateUser) {
    userRepository.save(updateUser);
    return "redirect:/users";
  }
}

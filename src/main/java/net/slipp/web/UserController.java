package net.slipp.web;

import javax.servlet.http.HttpSession;
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
  private UserRepository userRepository;

  @GetMapping("signUp")
  public String goToSignUpFormPage() {
    return "user/signUpForm";
  }

  @GetMapping("{id}/update")
  public String goToUpdateFormPage(@PathVariable Long id, Model model, HttpSession session) {
    if (!HttpSessionUtils.isLoginUser(session)) {
      System.out.println("Fail about go to update form page : Login failure");
      throw new IllegalStateException("로그인 하세요");
    }
    User sessionUser = HttpSessionUtils.getUserFromSession(session);

    if (!sessionUser.isCorrectId(id)) {
      System.out.println("Fail about go to update form page : only your own id");
      throw new IllegalStateException("본인 아이디만 수정 가능");
    }

    model.addAttribute("user", userRepository.findById(id).get());
    return "user/updateForm";
  }

  @GetMapping("")
  public String goToPrintUserListPage(Model model) {
    model.addAttribute("userList", userRepository.findAll());
    return "user/userList";
  }

  @GetMapping("loginForm")
  public String goToLoginFormPage() {
    return "user/loginForm";
  }

  @PostMapping("")
  public String signUpUser(User user, Model model) {
    System.out.println(user);
    userRepository.save(user);
    return "redirect:/users";
  }

  @PutMapping("{id}/update")
  public String updateUser(@PathVariable Long id, User updateUser, HttpSession session) {
    if (!HttpSessionUtils.isLoginUser(session)) {
      System.out.println("Fail about go to update form page : Login failure");
      throw new IllegalStateException("로그인 하세요");
    }
    User sessionUser = HttpSessionUtils.getUserFromSession(session);

    if (!sessionUser.isCorrectId(id)) {
      System.out.println("Fail about go to update form page : only your own id");
      throw new IllegalStateException("본인 아이디만 수정 가능");
    }

    userRepository.save(updateUser);
    return "redirect:/users";
  }

  @PostMapping("login")
  public String checkLogin(String userId, String userPassword, HttpSession session) {
    User toCheckUser = userRepository.findByUserId(userId);
    if (toCheckUser == null) {
      System.out.println("Login Failure : ID");
      return "redirect:/users/loginForm";
    }
    if (!toCheckUser.isCorrectPassword(userPassword)) {
      System.out.println("Login Failure : Password");
      return "redirect:/users/loginForm";
    }

    System.out.println("Login Success");
    session.setAttribute(HttpSessionUtils.USER_SESSION_KEY, toCheckUser);
    return "redirect:/";
  }

  @GetMapping("logout")
  public String logout(HttpSession session) {
    session.removeAttribute(HttpSessionUtils.USER_SESSION_KEY);
    return "redirect:/users/loginForm";
  }
}

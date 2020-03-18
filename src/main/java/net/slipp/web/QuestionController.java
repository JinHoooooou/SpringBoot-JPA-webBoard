package net.slipp.web;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/questions")
public class QuestionController {

  @GetMapping("questionForm")
  public String goToQuestionFormPage(HttpSession session) {
    if(!HttpSessionUtils.isLoginUser(session)) {
      System.out.println("Fail about go to update form page : Login failure");
      throw new IllegalStateException("로그인 하세요");
    }
    return "qna/questionForm";
  }


}

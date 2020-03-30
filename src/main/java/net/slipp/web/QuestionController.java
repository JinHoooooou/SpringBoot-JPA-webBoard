package net.slipp.web;

import javax.servlet.http.HttpSession;
import net.slipp.domain.Question;
import net.slipp.domain.QuestionRepository;
import net.slipp.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/questions")
public class QuestionController {

  @Autowired
  private QuestionRepository questionRepository;

  @GetMapping("createQuestion")
  public String goToQuestionFormPage(HttpSession session) {
    if (!HttpSessionUtils.isLoginUser(session)) {
      System.out.println("Fail about go to update form page : Login failure");
      throw new IllegalStateException("로그인 하세요");
    }
    return "qna/questionForm";
  }

  @GetMapping("{id}")
  public String goToReadingQuestionPage(@PathVariable Long id, Model model) {
    model.addAttribute("question", questionRepository.findById(id).get());
    return "qna/show";
  }

  @GetMapping("{id}/update")
  public String goToUpdateQuestionFormPage(@PathVariable Long id, Model model,
      HttpSession session) {
    if (!HttpSessionUtils.isLoginUser(session)) {
      throw new IllegalStateException("로그인 하세요");
    }
    User loginUser = HttpSessionUtils.getUserFromSession(session);
    Question question = questionRepository.getOne(id);
    if (!question.isSameWriter(loginUser)) {
      throw new IllegalStateException("본인이 쓴 글만 수정 할 수 있습니다.");
    }
    model.addAttribute("question", questionRepository.getOne(id));
    return "qna/updateQuestionForm";
  }

  @PostMapping("")
  public String createQuestion(String title, String contents, HttpSession session) {
    if (!HttpSessionUtils.isLoginUser(session)) {
      System.out.println("Fail about go to update form page : Login failure");
      throw new IllegalStateException("로그인 하세요");
    }
    User sessionUser = HttpSessionUtils.getUserFromSession(session);
    Question createdQuestion = new Question(sessionUser, title, contents);
    questionRepository.save(createdQuestion);

    return "redirect:/";
  }

  @PutMapping("{id}")
  public String updateQuestion(@PathVariable Long id, String title, String contents,
      HttpSession session) {
    if (!HttpSessionUtils.isLoginUser(session)) {
      throw new IllegalStateException("로그인 하세요");
    }
    User loginUser = HttpSessionUtils.getUserFromSession(session);
    Question question = questionRepository.getOne(id);
    if (!question.isSameWriter(loginUser)) {
      throw new IllegalStateException("본인이 쓴 글만 수정 할 수 있습니다.");
    }
    question.update(title, contents);
    questionRepository.save(question);
    return String.format("redirect:/questions/%d", id);
  }

  @DeleteMapping("{id}")
  public String deleteQuestion(@PathVariable Long id, HttpSession session) {
    if (!HttpSessionUtils.isLoginUser(session)) {
      throw new IllegalStateException("로그인 하세요");
    }
    User loginUser = HttpSessionUtils.getUserFromSession(session);
    Question question = questionRepository.getOne(id);
    if(!question.isSameWriter(loginUser)) {
      throw new IllegalStateException("본인이 쓴 글만 삭제 할 수 있습니다.");
    }
    questionRepository.deleteById(id);
    return "redirect:/";
  }
}

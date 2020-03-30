package net.slipp.web;

import javax.servlet.http.HttpSession;
import net.slipp.domain.Answer;
import net.slipp.domain.AnswerRepository;
import net.slipp.domain.Question;
import net.slipp.domain.QuestionRepository;
import net.slipp.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/questions/{questionId}/answers")
public class AnswerController {
  @Autowired
  private QuestionRepository questionRepository;

  @Autowired
  private AnswerRepository answerRepository;

  @PostMapping("")
  public String createComment(@PathVariable Long questionId, String contents, HttpSession session) {
    if(!HttpSessionUtils.isLoginUser(session)) {
      throw new IllegalStateException("로그인 하세요");
    }
    User loginUser = HttpSessionUtils.getUserFromSession(session);
    Question question = questionRepository.getOne(questionId);
    Answer answer = new Answer(loginUser, question, contents);
    answerRepository.save(answer);
    return String.format("redirect:/questions/%d", questionId);
  }

}

package net.slipp.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

  @GetMapping("/hello")
  public String helloWorldPage(Model model) {
    model.addAttribute("name", "Chris");
    model.addAttribute("value", 10000);
    model.addAttribute("taxed_value", 10000 - (10000 * 0.4));
    model.addAttribute("in_ca", true);
    return "welcome";
  }

}

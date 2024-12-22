package be.kdg.magiwastebackend.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PresentationController {

    @GetMapping("/team")
   public String getTeamPage() {
       return "team";
   }

   @GetMapping("/architecture")
   public String getArchitecturePage() {
        return "architecture";
   }

   @GetMapping("/expl-model")
   public String getModelPage() {
        return "expl-model";
   }

}

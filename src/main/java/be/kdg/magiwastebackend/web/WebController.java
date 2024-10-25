package be.kdg.magiwastebackend.web;

import be.kdg.magiwastebackend.domain.WasteBin;
import be.kdg.magiwastebackend.facade.ServiceFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WebController {

    private final ServiceFacade serviceFacade;

    public WebController(ServiceFacade serviceFacade) {
        this.serviceFacade = serviceFacade;
    }

    @GetMapping("/")
    public String getIndex() {
        return "index";
    }

    @GetMapping("bin/{binId}")
    public String getBin(@PathVariable("binId") Long binId, Model model){
        WasteBin bin = serviceFacade.findWasteBinById(binId);
        if (bin == null){
            return "error/404";
        }
        model.addAttribute(bin);
        return "bin";
    }
}

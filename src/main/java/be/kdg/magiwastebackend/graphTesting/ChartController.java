package be.kdg.magiwastebackend.graphTesting;

import be.kdg.magiwastebackend.domain.WasteBinEvent;
import be.kdg.magiwastebackend.service.WasteBinEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class ChartController {

    private final WasteBinEventService wasteBinEventService;

    @Autowired
    public ChartController(WasteBinEventService wasteBinEventService) {
        this.wasteBinEventService = wasteBinEventService;
    }

    @GetMapping("/chart2")
    public String getChartData(Model model) {
        List<WasteBinEvent> events = wasteBinEventService.findAll();
        System.out.println("Events from db: " + events);
        model.addAttribute("events", events);
        return "chart2";

    }
}


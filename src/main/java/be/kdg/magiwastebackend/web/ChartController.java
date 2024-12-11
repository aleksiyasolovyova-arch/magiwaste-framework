package be.kdg.magiwastebackend.web;

import be.kdg.magiwastebackend.domain.WasteBinEvent;
import be.kdg.magiwastebackend.domain.WeatherEvent;
import be.kdg.magiwastebackend.service.WasteBinEventService;
import be.kdg.magiwastebackend.service.WeatherEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class ChartController {

    private final WasteBinEventService wasteBinEventService;
    private final WeatherEventService weatherEventService;

    @Autowired
    public ChartController(WasteBinEventService wasteBinEventService, WeatherEventService weatherEventService) {
        this.wasteBinEventService = wasteBinEventService;
        this.weatherEventService = weatherEventService;
    }

    @GetMapping("/charts")
    public String getChartData(@RequestParam(required = false, defaultValue = "1") Long binId, Model model) {
        List<WasteBinEvent> events = wasteBinEventService.findAll();

        Map<Long, List<WasteBinEvent>> eventsByBin = events.stream()
                .collect(Collectors.groupingBy(event -> event.getBin().getId()))
                .entrySet()
                .stream()
                .limit(10)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        List<WasteBinEvent> oneBinEvent = events.stream()
                .filter(event -> event.getBin().getId().equals(binId))
                .collect(Collectors.toList());

        List<WeatherEvent> weatherEvents = weatherEventService.findAll();

        long totalBins = events.size();
        long totalTilts = events.stream().filter(
                event -> event.isTiltState()
        ).count();

        long totalWasteReceived = events.stream().filter(event -> event.getWasteReceived() == true).count();

        model.addAttribute("events", events);
        model.addAttribute("weatherEvents", weatherEvents);
        model.addAttribute("eventsByBin", eventsByBin);
        model.addAttribute("oneBinEvent", oneBinEvent);
        model.addAttribute("binId", binId);
        model.addAttribute("totalBins", totalBins);
        model.addAttribute("totalTilts", totalTilts);
        model.addAttribute("totalWasteReceived", totalWasteReceived);
        return "charts";

    }
}


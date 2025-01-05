package be.kdg.magiwastebackend.web;

import be.kdg.magiwastebackend.domain.AppUser;
import be.kdg.magiwastebackend.domain.WasteBinEvent;
import be.kdg.magiwastebackend.domain.WeatherEvent;
import be.kdg.magiwastebackend.service.WasteBinEventService;
import be.kdg.magiwastebackend.service.WeatherEventService;
import jakarta.servlet.http.HttpSession;
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
    public String getChartData(HttpSession session, @RequestParam(required = false) Long binId, Model model) {
        AppUser user = (AppUser) session.getAttribute("user");

        if (user == null || (!user.getPermissionLevel().equals("EMPLOYEE") && !user.getPermissionLevel().equals("ADMIN"))) {
            return "login"; // Redirect if not logged in or not an admin
        }
        model.addAttribute("isUserLoggedIn", true);

        List<WasteBinEvent> events = wasteBinEventService.findAll();

        Map<Long, List<WasteBinEvent>> eventsByBin = getEventsByBin(events);

        List<WasteBinEvent> oneBinEvent = getEventsForBin(events, binId);

        long totalBins = events.size();
        long totalTilts = countTilts(events);
        long totalWasteReceived = countWasteReceived(events);
        WasteBinEvent lastEvent = getLastEvent(oneBinEvent);
        Double lastPercentOfVolume = getLastPercentOfVolume(lastEvent);

        model.addAttribute("events", events);
        model.addAttribute("eventsByBin", eventsByBin);
        model.addAttribute("oneBinEvent", oneBinEvent);
        model.addAttribute("binId", binId);
        model.addAttribute("totalBins", totalBins);
        model.addAttribute("totalTilts", totalTilts);
        model.addAttribute("totalWasteReceived", totalWasteReceived);
        model.addAttribute("lastPercentOfVolume", lastPercentOfVolume);

        return "charts";
    }

    private Map<Long, List<WasteBinEvent>> getEventsByBin(List<WasteBinEvent> events) {
        return events.stream()
                .collect(Collectors.groupingBy(event -> event.getBin().getId()))
                .entrySet()
                .stream()
                .limit(35)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private List<WasteBinEvent> getEventsForBin(List<WasteBinEvent> events, Long binId) {
        return events.stream()
                .filter(event -> event.getBin().getId().equals(binId))
                .collect(Collectors.toList());
    }

    private long countTilts(List<WasteBinEvent> events) {
        return events.stream()
                .filter(WasteBinEvent::isTiltState)
                .count();
    }

    private long countWasteReceived(List<WasteBinEvent> events) {
        return events.stream()
                .filter(event -> event.getWasteReceived() == true)
                .count();
    }

    private WasteBinEvent getLastEvent(List<WasteBinEvent> events) {
        return events.isEmpty() ? null : events.get(events.size() - 1);
    }

    private Double getLastPercentOfVolume(WasteBinEvent lastEvent) {
        return lastEvent != null ? lastEvent.getPercentOfVolume() : 0.0;
    }
}


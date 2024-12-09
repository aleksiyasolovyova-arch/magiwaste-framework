package be.kdg.magiwastebackend.web;

import be.kdg.magiwastebackend.domain.WasteBin;
import be.kdg.magiwastebackend.domain.WasteBinEvent;
import be.kdg.magiwastebackend.facade.ServiceFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/dashboard")
    public String getDashboard(Model model) {
        List<WasteBin> bins = serviceFacade.findAllWasteBins();
        bins = bins.stream().filter(bin -> bin.getLatitude() != 0 && bin.getLongitude() != 0).toList();

        List<MapPoint> points = new ArrayList<>();

        bins.forEach(bin -> {
            points.add(new MapPoint(
                    bin.getLatitude(),
                    bin.getLongitude(),
                    (int) bin.getPercentOfVolume(),
                    bin.getAddress()
            ));
// this code is for compiling latest bin events into the bins
//            WasteBinEvent latestEvent = serviceFacade.findAllWasteBinEventsByWasteBin(bin).getLast();
//            if (latestEvent != null){
//
//                double percentOfVolume = latestEvent.getPercentOfVolume();
//                bin.setPercentOfVolume(percentOfVolume);
//                serviceFacade.saveWasteBin(bin);
//
//                points.add(
//                        new MapPoint(
//                                bin.getLatitude(),
//                                bin.getLongitude(),
//                                (int) percentOfVolume,
//                                bin.getAddress()
//                        )
//                );
//            }
        });

        model.addAttribute("points", points);

        return "dashboard";
    }

    @GetMapping("bin/{binId}")
    public String getBin(@PathVariable("binId") Long binId, Model model) {
        WasteBin bin = serviceFacade.findWasteBinById(binId);
        if (bin == null) {
            return "error/404";
        }
        model.addAttribute(bin);
        return "bin";
    }
}

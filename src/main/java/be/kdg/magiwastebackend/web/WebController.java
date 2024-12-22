package be.kdg.magiwastebackend.web;

import be.kdg.magiwastebackend.domain.AppUser;
import be.kdg.magiwastebackend.domain.WasteBin;
import be.kdg.magiwastebackend.facade.ServiceFacade;
import be.kdg.magiwastebackend.usersecurity.AppUserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WebController {

    private final ServiceFacade serviceFacade;
    private final AppUserService appUserService;

    public WebController(ServiceFacade serviceFacade, AppUserService appUserService) {
        this.serviceFacade = serviceFacade;
        this.appUserService = appUserService;
    }

    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        AppUser user = (AppUser) session.getAttribute("user");

        if (user == null || (!user.getPermissionLevel().equals("EMPLOYEE") && !user.getPermissionLevel().equals("ADMIN"))) {
            return "index"; // Redirect if not logged in or not an admin
        }
        model.addAttribute("isUserLoggedIn", true);
        return "index";
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
        AppUser user = appUserService.authenticate(email, password);

        if (user != null) {
            session.setAttribute("user", user); // Store user details in session
            System.out.println("User logged in: " + user);
            return "redirect:/dashboard";
        }

        model.addAttribute("error", "Invalid credentials");
        return "login"; // Return login page with an error message
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Destroy the session
        return "redirect:/"; // Redirect to index.html
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        AppUser user = (AppUser) session.getAttribute("user");

        if (user == null || (!user.getPermissionLevel().equals("EMPLOYEE") && !user.getPermissionLevel().equals("ADMIN"))) {
            return "redirect:/login"; // Redirect if not logged in or not an admin
        }
        model.addAttribute("isUserLoggedIn", true);

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
/* this code is for compiling latest bin events into the bins
            WasteBinEvent latestEvent = serviceFacade.findAllWasteBinEventsByWasteBin(bin).getLast();
            if (latestEvent != null){

                double percentOfVolume = latestEvent.getPercentOfVolume();
                bin.setPercentOfVolume(percentOfVolume);
                serviceFacade.saveWasteBin(bin);

                points.add(
                        new MapPoint(
                                bin.getLatitude(),
                                bin.getLongitude(),
                                (int) percentOfVolume,
                                bin.getAddress()
                        )
                );
            }*/
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

    @GetMapping("/team")
    public String getTeamPage(HttpSession session, Model model) {
        AppUser user = (AppUser) session.getAttribute("user");

        if (user == null || (!user.getPermissionLevel().equals("EMPLOYEE") && !user.getPermissionLevel().equals("ADMIN"))) {
            return "redirect:/login"; // Redirect if not logged in or not an admin
        }
        model.addAttribute("isUserLoggedIn", true);
        return "team";
    }

    @GetMapping("/architecture")
    public String getArchitecturePage(HttpSession session, Model model) {
        AppUser user = (AppUser) session.getAttribute("user");

        if (user == null || (!user.getPermissionLevel().equals("EMPLOYEE") && !user.getPermissionLevel().equals("ADMIN"))) {
            return "redirect:/login"; // Redirect if not logged in or not an admin
        }
        model.addAttribute("isUserLoggedIn", true);
        return "architecture";
    }

    @GetMapping("/expl-model")
    public String getModelPage(HttpSession session, Model model) {
        AppUser user = (AppUser) session.getAttribute("user");

        if (user == null || (!user.getPermissionLevel().equals("EMPLOYEE") && !user.getPermissionLevel().equals("ADMIN"))) {
            return "redirect:/login"; // Redirect if not logged in or not an admin
        }
        model.addAttribute("isUserLoggedIn", true);
        return "expl-model";
    }
}

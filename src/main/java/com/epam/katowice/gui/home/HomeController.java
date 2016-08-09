package com.epam.katowice.gui.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * RestController of Movies Rental.
 *
 * Created by Adam_Skowron on 08.08.2016.
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    String index() {
        return "pages/home";
    }

}

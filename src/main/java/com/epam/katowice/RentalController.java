package com.epam.katowice;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController of Movies Rental.
 *
 * Created by Adam_Skowron on 08.08.2016.
 */
@RestController
@RequestMapping("/")
public class RentalController {

    @RequestMapping(method = RequestMethod.GET)
    public String greeting() {
        return "Welcome in Movies Rental!";
    }

}

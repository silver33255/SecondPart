package com.panaskin.secureapp.controller;

import com.panaskin.secureapp.entity.FilmSession;
import com.panaskin.secureapp.service.EntityService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Log4j2
@Controller
public class FilmSessionController {

    @Autowired
    private EntityService<FilmSession> sessionService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homepage(Model model, HttpServletRequest httpServletRequest) {
        if (httpServletRequest.isUserInRole("ADMIN")) {
            return "redirect:/admin";
        } else if (httpServletRequest.isUserInRole("USER")) {
            return "redirect:/user";
        }
        model.addAttribute("filmSessions",
                sessionService.receiveAll());
        return "homepage";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String userPage(Model model) {
        model.addAttribute("filmSessions",
                sessionService.receiveAll());
        return "user";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model) {
        model.addAttribute("filmSessions",
                sessionService.receiveAll());
        return "admin";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        return "login";
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String error(Model model) {
        return "error";
    }

    @RequestMapping(value = "/addSesssion", method = RequestMethod.POST)
    String addSession(@RequestParam String film, @RequestParam String start, Model model) {
        FilmSession filmSession = null;
        Time time = null;
        try {
            long mls = new SimpleDateFormat("HH:mm").parse(start).getTime();
            time = new Time(mls);
        } catch (ParseException ex) {
            log.info("Wrong time formate" + ex.getMessage());
        }
        filmSession = FilmSession.builder().film(film).start(time).build();
        sessionService.add(filmSession);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/sessions/booking/", method = RequestMethod.POST)
    String sessionBooking(@RequestParam Long bookedSessionId) {
        return "seccessful";
    }
}
package org.gui.example.web.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LogoutController {

    private static final Logger LOG = LoggerFactory.getLogger(LogoutController.class);

    /**
     * 退出系统
     * 
     * @author gui
     * */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public ModelAndView exit(HttpSession session) {
        LOG.debug("logout");
        session.invalidate();
        final ModelAndView ret = new ModelAndView(new RedirectView("login.html"));
        return ret;
    }

}

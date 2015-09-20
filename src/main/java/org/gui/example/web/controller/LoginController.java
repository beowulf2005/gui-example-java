package org.gui.example.web.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.gui.example.data.to.LoginForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import org.gui.example.entity.user.TUser;
import org.gui.example.service.UserSecurityService;
import org.gui.example.service.user.UserService;
import org.gui.example.util.Utils;

@Controller
public class LoginController {

    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

    private static final String VIEW_NAME = "login";

    @Inject
    private UserService userService;

    /*
     * 用户登录
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ModelAndView login(HttpSession session, @Valid LoginForm form, BindingResult result) {
        LOG.debug("login {}", form);
        if (result.hasErrors()) {
            return Utils.backToViewWithForm(VIEW_NAME, form, result, null, null);
        }

        if (!Utils.checkAndRemoveVerifyCode(session, form.getSign())) {
            return Utils.backToViewWithForm(VIEW_NAME, form, result, "sign", "valid.loginForm.sign.incorrect");
        }

        TUser user = null;
        // login,password are fields of form
        if (form.isEmail()) {
            user = userService.getUserRepository().findByEmail(form.getLogin());
            if (user == null) {
                return Utils
                        .backToViewWithForm(VIEW_NAME, form, result, "login", "valid.loginForm.login.unknown_email");
            }
        } else {
            user = userService.getUserRepository().findByUsername(form.getLogin());
            if (user == null) {
                return Utils.backToViewWithForm(VIEW_NAME, form, result, "login",
                        "valid.loginForm.login.unknown_username");
            }
        }

        final boolean pwdChecked = userSecurityService.checkPassword(user, form.getPassword());
        if (!pwdChecked) {
            return Utils.backToViewWithForm(VIEW_NAME, form, result, "password", "valid.loginForm.password.incorrect");
        }

        session.setAttribute("user", user);
        final String view = Utils.teamworkView("home", "main", null, null, null, null);
        return new ModelAndView(view);
    }
}

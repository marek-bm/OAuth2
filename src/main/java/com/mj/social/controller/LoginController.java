package com.mj.social.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
    private static final String baseUrl = "/oauth2/authorize-client";
    Map<String, String> oauth2AuthenticationUrls=new HashMap<>();

    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;

    @GetMapping("/login")
    public String getLoginPage(Model model){
        Iterable<ClientRegistration> clientRegistrations =(Iterable<ClientRegistration>) clientRegistrationRepository;
        clientRegistrations.forEach(registration ->
            oauth2AuthenticationUrls.put(
                    registration.getClientName(),
                    baseUrl+"/"+registration.getRegistrationId()));
        model.addAttribute("urls", oauth2AuthenticationUrls);
        return "login-page";
    }
}

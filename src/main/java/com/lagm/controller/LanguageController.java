package com.lagm.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

@RestController
@RequestMapping("/languages")
@RequiredArgsConstructor
public class LanguageController {
    public final LocaleResolver localeResolver;
    private final HttpServletRequest httpServletRequest;
    private final HttpServletResponse httpServletResponse;

    @GetMapping("/locale/{locale}")
    public ResponseEntity<Void> changeLocale(@PathVariable String locale) {
        Locale userLocale = switch (locale) {
            case "en" -> Locale.ENGLISH;
            case "fr" -> Locale.FRENCH;
            default -> Locale.ROOT;
        };

        localeResolver.setLocale(httpServletRequest, httpServletResponse, userLocale);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

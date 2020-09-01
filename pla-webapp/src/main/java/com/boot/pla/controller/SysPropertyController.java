package com.boot.pla.controller;

import java.util.Properties;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys-properties")
@ResponseStatus(HttpStatus.OK)
public class SysPropertyController {

    @GetMapping
    public Properties getSystemProperties() {
        return System.getProperties();
    }

    @GetMapping(value = "/{name}")
    public String getSystemProperty(@PathVariable String name) {
        return System.getProperty(name);
    }
}
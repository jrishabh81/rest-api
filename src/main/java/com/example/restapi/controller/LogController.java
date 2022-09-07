package com.example.restapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.logging.LogLevel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/log")
public class LogController {

    @GetMapping
    public void log() {
        log.trace("trace log");
        log.debug("debug log");
        log.info("info log");
        log.error("error log");
        log.warn("warn log");
    }

    @GetMapping("/ex")
    public void logEx() throws IOException {
        log.error("Exception occurred", new RuntimeException("Error fetching data"));
        throw new IOException("File not found");
    }
}

package com.example.music_sharing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {

    @GetMapping("/main")
    public String main() {
        return "main";
    }

    @GetMapping("/write")
    public String write() {
        return "write";
    }

    @GetMapping("/view/{id}")
    public String view(@PathVariable String id) {
        return "view";
    }

    @GetMapping("/main/singer/{singer}")
    public String signer(@PathVariable String singer) {
        return "singer";
    }

    @GetMapping("/main/song/{song}")
    public String song(@PathVariable String song) {
        return "song";
    }

}

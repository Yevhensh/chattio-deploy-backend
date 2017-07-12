package com.chattio.controller;

import com.chattio.dto.SampleDto;
import com.chattio.service.DialogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {

    @Autowired
    private DialogService dialogService;

    @GetMapping("/")
    public String base() {
        return "SERVER WORKS!";
    }

    @GetMapping("/test")
    public List<SampleDto> testMeth() {
        List<SampleDto> samples = new ArrayList<>();
        samples.add(new SampleDto("name", 5));
        samples.add(new SampleDto("age", 40));
        return samples;
    }
}

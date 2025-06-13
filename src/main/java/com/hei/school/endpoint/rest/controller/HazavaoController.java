package com.hei.school.endpoint.rest.controller;

import com.hei.school.service.HazavaoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class HazavaoController {

    private final HazavaoService hazavaoService;

    @GetMapping("/hazavao")
    public String hazavao(@RequestParam String teny) {
        return hazavaoService.hazavaoTeny(teny);
    }
}

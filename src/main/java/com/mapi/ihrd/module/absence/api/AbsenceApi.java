package com.mapi.ihrd.module.absence.api;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/absence")
public class AbsenceApi {

    @GetMapping
    public ResponseEntity index(){
        return ResponseEntity.ok().build();
    }
}

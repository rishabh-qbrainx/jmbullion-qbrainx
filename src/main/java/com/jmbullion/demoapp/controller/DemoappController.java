package com.jmbullion.demoapp.controller;

import com.jmbullion.demoapp.model.FinalOrder;
import com.jmbullion.demoapp.service.IDemoappService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.jmbullion.demoapp.utils.DemoappConstants.API;
import static com.jmbullion.demoapp.utils.DemoappConstants.ORDER;

@RestController
@RequestMapping(value = API)
@RequiredArgsConstructor
public class DemoappController {

    private final IDemoappService service;

    @PostMapping(value = ORDER, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> finalOrderAPI(@Valid @RequestBody FinalOrder finalOrder) {

        String orderResponse = service.finalOrder(finalOrder);
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }

}

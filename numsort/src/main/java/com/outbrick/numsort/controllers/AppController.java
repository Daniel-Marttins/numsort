package com.outbrick.numsort.controllers;

import com.outbrick.numsort.components.AppProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/api/app")
public class AppController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String appTestRequest() {
        return "Validation";
    }

}

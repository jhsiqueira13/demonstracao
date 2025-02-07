package com.jeriam.demonstracao.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jeriam
 */
@RestController
@RequestMapping("")
public class HealthController {
    
    
    @RequestMapping(path = "/health", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> health() {
        return ResponseEntity.ok("Instance Health");
    }

}

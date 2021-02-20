package com.zyonicsoftware.jensbot.restapi;

import com.zyonicsoftware.jensbot.util.MySQLManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class RestAPIController {

    public static MySQLManager mySQLManager;
    public static ArrayList<String> allowedKeys;

    @PostMapping("/jensiyootoob/twdc/newcommand/")
    public ResponseEntity<String> setNewCommand(@RequestHeader final String Authorization, final String name, final String response) {
        String token = Authorization.replace("Bearer ", "");
        if(allowedKeys.contains(token)) {
            mySQLManager.addNewCommand(name, response);
        }
        return ResponseEntity.status(HttpStatus.OK).body("{ \"status\" : handled }");
    }

    @PostMapping("/jensiyootoob/twdc/delcommand/")
    public ResponseEntity<String> deleteCommand(@RequestHeader final String Authorization, final String name) {
        String token = Authorization.replace("Bearer ", "");
        if(allowedKeys.contains(token)) {
            mySQLManager.removeCommand(name);
        }
        return ResponseEntity.status(HttpStatus.OK).body("{ \"status\" : handled }");
    }
}

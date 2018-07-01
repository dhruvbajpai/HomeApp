package com.application.homeapp.firstaws.controller;

import com.application.homeapp.firstaws.Entity.Student;
import com.application.homeapp.firstaws.SmsSender;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class MainController {


    @RequestMapping(value = "/cooltext/{num}/{phone}",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Student getStudent(@PathVariable("num") int number, @PathVariable("phone") String phoneNumber)
    {
        try {
            System.out.println(phoneNumber);
            String toPhoneNumber = phoneNumber.length() < 11 ? "+12132841797" : phoneNumber;
            System.out.println(phoneNumber);
            System.out.println(toPhoneNumber);
            new SmsSender().sendMessage(number, toPhoneNumber);
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return new Student("Dhruv",10);
    }

    @RequestMapping(value = "/test",method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE})
    public Student returnStudent()
    {
        return new Student("Isha",11);
    }


}


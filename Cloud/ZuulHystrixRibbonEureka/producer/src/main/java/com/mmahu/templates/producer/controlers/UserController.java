package com.mmahu.templates.producer.controlers;

import com.mmahu.templates.producer.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
public class UserController {

    @RequestMapping("/users")
    public @ResponseBody List<User> getUsers(@Value("${company:default}") String company) {
        return Arrays.asList(
                new User("Jhon","Smith", company),
                new User("Mark","Pointer", company)
        );
    }

}

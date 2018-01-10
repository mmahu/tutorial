package com.mmahu.templates.redis.controllers;

import com.mmahu.templates.redis.model.Person;
import com.mmahu.templates.redis.repo.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class PersonController {

    @Autowired
    public PersonRepository repo;

    @PutMapping("/person")
    public void save(@RequestBody Person person) {
        repo.save(person);
    }

    @DeleteMapping("/person")
    public void delete(@RequestBody Person person) {
        repo.delete(person);
    }

    @GetMapping("/people")
    public @ResponseBody List<Person> listPersons() {
        List<Person> people = new ArrayList<>();
        Iterator<Person> iterator = repo.findAll().iterator();
        while (iterator.hasNext()) {
            people.add(iterator.next());
        }
        return people;
    }

}

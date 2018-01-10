package com.mmahu.templates.redis.controllers;

import com.mmahu.templates.redis.model.Address;
import com.mmahu.templates.redis.model.AddressRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.DeclareRoles;
import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    @Qualifier("address")
    private RedisTemplate<String, Address> template;

    @PutMapping
    public void save(@RequestBody Address address) {
        template.boundListOps("address").leftPush(address);
    }

    @DeleteMapping
    public void delete() {
        template.boundListOps("address").rightPop();
    }

    @GetMapping
    public @ResponseBody List<Address> get() {
        return template.boundListOps("address").range(0, -1);
    }
}

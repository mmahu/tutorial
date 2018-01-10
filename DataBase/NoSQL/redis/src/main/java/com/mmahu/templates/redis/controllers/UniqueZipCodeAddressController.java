package com.mmahu.templates.redis.controllers;

import com.mmahu.templates.redis.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address/zip")
public class UniqueZipCodeAddressController {

    @Autowired
    @Qualifier("address")
    private RedisTemplate<String, Address> template;

    @PutMapping
    public void put(@RequestBody Address address) {
        template.boundValueOps(address.getZip()).set(address);
    }

    @GetMapping
    public @ResponseBody Address get(@RequestParam("code") String code) {
        return template.boundValueOps(code).get();
    }

    @DeleteMapping
    public void delete(@RequestParam("code") String code) {
        template.delete(code);
    }

}

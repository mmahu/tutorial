package com.mmahu.templates.mongodb.controllers;

import com.mmahu.templates.mongodb.dao.CustomerDao;
import com.mmahu.templates.mongodb.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerDao customerDao;

    @GetMapping
    public @ResponseBody List<Customer> listCustomers() {
        return customerDao.findAll();
    }

    @PutMapping
    public void addCustomer(@RequestBody Customer customer) {
        customerDao.save(customer);
    }


    @DeleteMapping
    public void delete(@RequestBody Customer customer) {
        customerDao.delete(customer);
    }

}

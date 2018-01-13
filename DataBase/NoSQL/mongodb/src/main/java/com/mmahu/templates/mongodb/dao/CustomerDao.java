package com.mmahu.templates.mongodb.dao;

import com.mmahu.templates.mongodb.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerDao extends MongoRepository<Customer, String> {
}

package com.mmahu.templates.redis.repo;

import com.mmahu.templates.redis.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, String> {
}

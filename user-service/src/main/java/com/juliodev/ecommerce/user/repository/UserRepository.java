package com.juliodev.ecommerce.user.repository;

import com.juliodev.ecommerce.user.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> { }
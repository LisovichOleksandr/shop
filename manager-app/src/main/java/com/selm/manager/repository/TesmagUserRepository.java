package com.selm.manager.repository;

import com.selm.manager.entity.TesmagUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TesmagUserRepository extends CrudRepository<TesmagUser, Integer> {

    Optional<TesmagUser> findByUsername(String username);
}

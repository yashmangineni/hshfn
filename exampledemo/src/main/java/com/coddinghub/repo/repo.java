package com.coddinghub.repo;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.coddinghub.model.login;

@Repository
public interface repo extends CrudRepository<login,Long> {

	List<login> findByEmailAndPassword(String email, long password);

}

package com.coddinghub.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coddinghub.model.login;
import com.coddinghub.repo.repo;

@Service 
public class service implements serviceinterface {
@Autowired
repo re;
@Override
public login save(login l) {
	return re.save(l);
}

@Override
public List<login> getdata() {
	return (List<login>) re.findAll();
}

@Override
public boolean authenticateUser(String email, long password) {
	List<login> users = re.findByEmailAndPassword(email, password);
    return !users.isEmpty();
}

}

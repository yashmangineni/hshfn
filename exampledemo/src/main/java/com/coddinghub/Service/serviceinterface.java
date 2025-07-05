package com.coddinghub.Service;

import java.util.List;

import com.coddinghub.model.login;

public interface serviceinterface {

	login save(login l);

	List<login> getdata();

	boolean authenticateUser(String email, long password);

}

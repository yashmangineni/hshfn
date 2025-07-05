package com.coddinghub.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.coddinghub.model.employee;

public interface empserviceinterface {

	employee saveEmployee(employee employee, MultipartFile document) throws IOException;

	Page<employee> searchEmployees(String keyword, int page, int size);

	employee getEmployeeById(Long id);



void deleteEmployee(Long id);

employee updateEmployee(Long id, employee updatedEmployee);

List<employee> getAllEmployees();


	


}

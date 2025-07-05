package com.coddinghub.Service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.coddinghub.model.employee;
import com.coddinghub.repo.emprepo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service 
public class empservice implements empserviceinterface {
	@Autowired
	emprepo re;

	@Override
	public employee saveEmployee(employee employee, MultipartFile document) throws IOException{
		if (employee.getDateOfBirth().isAfter(LocalDate.now().minusYears(18))) {
            throw new IllegalArgumentException("Employee must be at least 18 years old");
        }
		// Generate Employee ID
        employee latest = re.findTopByOrderByIdDesc();
        long newId = (latest == null) ? 1 : latest.getId() + 1;
        employee.setEmployeeId("EMP" + String.format("%03d", newId));

        // Generate unique login ID
        String baseLoginId = (employee.getFirstName().charAt(0) + employee.getLastName()).toLowerCase();
        String loginId = baseLoginId;
        Random random = new Random();
        while (re.existsByLoginId(loginId)) {
            loginId = baseLoginId + (100 + random.nextInt(900));
        }
        employee.setLoginId(loginId);

        // Validate file
        if (!document.getOriginalFilename().endsWith(".pdf") || document.getSize() < 10_000 || document.getSize() > 1_000_000) {
            throw new IllegalArgumentException("PDF only. Size must be between 10KB and 1MB.");
        }

        // Save document
        String filePath = "uploads/" + document.getOriginalFilename();
        Path path = Paths.get(filePath);
        Files.createDirectories(path.getParent());
        Files.write(path, document.getBytes());

        employee.setDocumentPath(filePath);

        return re.save(employee);
    }

	@Override
	public Page<employee> searchEmployees(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        if (keyword == null || keyword.isEmpty()) {
            return re.findAll(pageable);
        }
        return re.searchEmployees(keyword, pageable);
    }

	@Override
	public employee getEmployeeById(Long id) {
		 try {
			return re.findById(id).orElse(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	

	@Override
	public void deleteEmployee(Long id) {
		re.deleteById(id);
		
	}

	@Override
	public employee updateEmployee(Long id, employee updatedEmployee) {
	    Optional<employee> optionalEmployee = re.findById(id);
	    if (!optionalEmployee.isPresent()) {
	        return null;  // Or throw custom exception
	    }

	    employee existing = optionalEmployee.get();

	    existing.setFirstName(updatedEmployee.getFirstName());
	    existing.setMiddleName(updatedEmployee.getMiddleName());
	    existing.setLastName(updatedEmployee.getLastName());
	    existing.setDateOfBirth(updatedEmployee.getDateOfBirth());
	    existing.setDepartment(updatedEmployee.getDepartment());
	    existing.setSalary(updatedEmployee.getSalary());
	    existing.setPermanentAddress(updatedEmployee.getPermanentAddress());
	    existing.setCurrentAddress(updatedEmployee.getCurrentAddress());
	    existing.setDocumentPath(updatedEmployee.getDocumentPath());

	    return re.save(existing);
	}

	@Override
	public List<employee> getAllEmployees() {
		
		return re.findAll();
	}




}

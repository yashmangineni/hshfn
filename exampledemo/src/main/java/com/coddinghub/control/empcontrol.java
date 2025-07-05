package com.coddinghub.control;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.coddinghub.Service.empserviceinterface;
import com.coddinghub.model.employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*")
public class empcontrol {
	@Autowired
	empserviceinterface eser;
	@PostMapping
	public employee addemployee(
	    @RequestPart("employee") String employeeJson,
	    @RequestPart("document") MultipartFile document
	) throws Exception {
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.registerModule(new JavaTimeModule());
	    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

	    employee employee = mapper.readValue(employeeJson, employee.class);
	    return eser.saveEmployee(employee, document);
	}
	
	@GetMapping("/search")
    public Page<employee> searchEmployees(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return eser.searchEmployees(keyword, page, size);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<employee> getEmployeeById(@PathVariable Long id) {
        employee emp = eser.getEmployeeById(id);
        if (emp != null) {
            return ResponseEntity.ok(emp); // 200 OK with employee data
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<employee> updateEmployee(
        @PathVariable Long id,
        @RequestBody employee updatedEmployee) {

        employee emp = eser.updateEmployee(id, updatedEmployee);
        if (emp == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(emp);
    }
  
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        eser.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/all")
    public List<employee> getAllEmployees() {
        return eser.getAllEmployees();
    }
}

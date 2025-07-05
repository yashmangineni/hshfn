package com.coddinghub.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.coddinghub.model.employee;
@Repository
public interface emprepo extends JpaRepository<employee,Long> {

	employee findTopByOrderByIdDesc();

	boolean existsByLoginId(String loginId);
	@Query("SELECT e FROM employee e WHERE " +
	           "LOWER(e.firstName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
	           "LOWER(e.middleName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
	           "LOWER(e.lastName) LIKE LOWER(CONCAT('%', :keyword, '%'))")

	Page<employee> searchEmployees(String keyword, Pageable pageable);

}

package com.openclassrooms.APISpringBoot.repository;

import com.openclassrooms.APISpringBoot.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long>{

}
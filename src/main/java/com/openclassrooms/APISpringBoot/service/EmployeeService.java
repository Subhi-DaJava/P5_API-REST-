package com.openclassrooms.APISpringBoot.service;

import com.openclassrooms.APISpringBoot.model.Employee;
import com.openclassrooms.APISpringBoot.repository.EmployeeRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * lorsqu’une requête est réceptionnée, la couche controller délègue les traitements métiers à exécuter.
 * La couche service pourra ensuite faire appel à la couche repository. La couche service est
 * également un pont entre le controller et le repository.
 */
//Data est une annotation Lombok. Nul besoin d’ajouter les getters et les setters
@Data
/*
Un petit zoom sur l’annotation @Service : tout comme l’annotation @Repository, c’est une spécialisation de @Component.
Son rôle est donc le même, mais son nom a une valeur sémantique pour ceux qui lisent votre code. Pour le reste,
ce code devrait être à votre portée.
 */
@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Optional<Employee> getEmployees(final Long id){
        return employeeRepository.findById(id);
    }

    public Iterable<Employee> getEmployees(){
        return employeeRepository.findAll();
    }

    public void deleteEmployee(final Long id ){
        employeeRepository.deleteById(id);
    }

    public Employee saveEmployee(Employee employee){
        Employee savedEmployee = employeeRepository.save(employee);
        return savedEmployee;
    }
}

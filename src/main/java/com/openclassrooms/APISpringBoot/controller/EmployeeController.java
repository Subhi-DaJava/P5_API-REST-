package com.openclassrooms.APISpringBoot.controller;

import com.openclassrooms.APISpringBoot.model.Employee;
import com.openclassrooms.APISpringBoot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * ’un des avantages de Spring et Spring Boot est de vous fournir les composants logiciels qui vous évitent de faire du code complexe !
 * Le starter “spring-boot-starter-web” nous fournit justement tout le nécessaire pour créer un endpoint.
 */

/**
 * @RestController atteint 2 objectifs :
 * Comme @Component, elle permet d’indiquer à Spring que cette classe est un bean.
 *Elle indique à Spring d’insérer le retour de la méthode au format JSON dans le corps de la réponse HTTP.
 * Grâce à ce deuxième point, les applications qui vont communiquer avec votre API accéderont au résultat de leur requête
 * en parsant la réponse HTTP.
 */

@RestController
public class EmployeeController {
    /*
    Une classe Java annotée @RestController ;
    Que les méthodes de la classe soient annotées. Chaque méthode annotée devient alors un endpoint grâce aux annotations @GetMapping,
    @PostMapping, @PatchMapping, @PutMapping, @DeleteMapping, @RequestMapping.
     */
    /**
     * Injecté une instance d'EmployeeService. Cela permettra d’appeler les méthodes pour communiquer
     * avec la base de données.
     */
    @Autowired
    private EmployeeService employeeService;
    /**
     * Read - Get all employees
     * @return - An Iterable object of Employee full filled
     */
    //créé une méthode getEmployees() annotée @GetMapping(“/employees”).
    /**
     * Cela signifie que les requêtes HTTP de type GET à l’URL /employees exécuteront le code de cette méthode.
     * Et ce code est tout simple : il s’agit d’appeler la méthode getEmployees() du service, ce dernier appellera la méthode
     * findAll() du repository, et nous obtiendrons ainsi tous les employés enregistrés en base de données.
     * @return
     */

    @PostMapping("/employee")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }


    /**
     * Read - Get one employee
     * @param id The id of the employee
     * @return An Employee object full filled
     */
    @GetMapping("/employee/{id}")
    public Employee getEmployee(@PathVariable("id") final Long id) {
        Optional<Employee> employee = employeeService.getEmployee(id);
        if(employee.isPresent()) {
            return employee.get();
        } else {
            return null;
        }
        /*
        Proposé par le système : return employee.orElse(null); replace by "condition with functional style expression"
         */
    }

    /**
     * Read - Get all employees
     * @return - An Iterable object of Employee full filled
     */
    @GetMapping("/employees")
    public Iterable<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    /**
     * Update - Update an existing employee
     * @param id - The id of the employee to update
     * @param employee - The employee object updated
     * @return
     */
    @PutMapping("/employee/{id}")
    public Employee updateEmployee(@PathVariable("id") final Long id, @RequestBody Employee employee) {
        Optional<Employee> e = employeeService.getEmployee(id);
        if(e.isPresent()) {
            Employee currentEmployee = e.get();

            String firstName = employee.getFirstName();
            if(firstName != null) {
                currentEmployee.setFirstName(firstName);
            }
            String lastName = employee.getLastName();
            if(lastName != null) {
                currentEmployee.setLastName(lastName);
            }
            String mail = employee.getMail();
            if(mail != null) {
                currentEmployee.setMail(mail);
            }
            String password = employee.getPassword();
            if(password != null) {
                currentEmployee.setPassword(password);
            }
            employeeService.saveEmployee(currentEmployee);
            return currentEmployee;
        } else {
            return null;
        }
    }


    /**
     * Delete - Delete an employee
     * @param id - The id of the employee to delete
     */
    @DeleteMapping("/employee/{id}")
    public void deleteEmployee(@PathVariable("id") final Long id) {
        employeeService.deleteEmployee(id);
    }
}

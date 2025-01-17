package com.example.hrPortal.hrms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hrPortal.hrms.model.Candidate;
import com.example.hrPortal.hrms.model.Employee;
import com.example.hrPortal.hrms.model.HR;
import com.example.hrPortal.hrms.service.CandidateService;
import com.example.hrPortal.hrms.service.EmployeeService;
import com.example.hrPortal.hrms.service.HrService;
import java.util.List;

@RestController
@RequestMapping("/api/hr")
@PreAuthorize("hasRole('HR')")
public class HrController {

	@Autowired
	private HrService hrService;
	
	@Autowired
	private CandidateService candidateService;
	
    @Autowired
    private EmployeeService employeeService;
	
   
    

//    @PutMapping("/{id}")
//    public HR updateHR(@PathVariable Long id, @RequestBody HR hrDetails) {
//        return hrService.updateHR(id, hrDetails);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteHR(@PathVariable Long id) {
//        hrService.deleteHR(id);
//	
//    }
	
    @PostMapping("/candidates")
    public ResponseEntity<?> createCandidate(@RequestBody Candidate candidate) {
        Candidate savedCandidate = candidateService.saveCandidate(candidate);
        return ResponseEntity.ok(savedCandidate);
    }
    @GetMapping("/candidates")
    public ResponseEntity<List<Candidate>> getAllCandidates() {
        List<Candidate> candidates = candidateService.getAllCandidates();
        return ResponseEntity.ok(candidates);
    }
    
    @GetMapping(path = "/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
    
    @GetMapping(path = "/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employee);
    }

    @PostMapping("/employees")
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeeService.saveEmployee(employee);
        return ResponseEntity.ok(savedEmployee);
    }
    
    @PutMapping(path = "/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        Employee updatedEmployee = employeeService.updateEmployee(id, employeeDetails);
        if (updatedEmployee == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping(path = "/employees/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}

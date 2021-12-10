package at.kaindorf.controller;

import at.kaindorf.pojos.Department;
import at.kaindorf.pojos.Employee;
import at.kaindorf.repo.DepartmentRepository;
import at.kaindorf.repo.EmployeeRepository;
import at.kaindorf.repo.InitDB;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequestMapping("employee")
@SessionAttributes("selectedDepartment")
public class employeeController {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @ModelAttribute
    public void addAttributes(Model model) {
        List<Department> departments = departmentRepository.findAll();
        model.addAttribute("employees", departments.get(0).getEmployees());
        model.addAttribute("selectedDepartment", new Department());
        //Get all sorted departmentNames
        model.addAttribute("departments", departments.stream().map(Department::getDeptName).collect(Collectors.toList()).stream().sorted().collect(Collectors.toList()));
        model.addAttribute("selectedEmployee", new Employee());
        model.addAttribute("newEmployee", new Employee());
    }

    @PostMapping("/sortAddEmployee")
    public String sortEmployees(@SessionAttribute Department selectedDepartment, Model model, @RequestParam("buttonText") String buttonText) {
        switch (buttonText) {
            /*case "sort":
                List<Employee> employees;
                System.out.println(selectedDepartment);
                if (selectedDepartment == null) {
                    employees = employeeRepository.findEmployeesByDepartmentSorted("Customer Service");
                } else {
                    employees = employeeRepository.findEmployeesByDepartmentSorted(selectedDepartment.getDeptName());
                }
                System.out.println(employees);
                model.addAttribute("employees", employees);
                break;*/

            case "create":
                return "redirect:/employee/form";

        }
        return "employeeView";
    }

    @PostMapping("/getEmployees")
    public String getEmployees(@ModelAttribute("selectedDepartment") Department department, Model model) {
        System.out.println(department);
        List<Employee> employees = employeeRepository.findEmployeesByDepartmentSorted(department.getDeptName());
        model.addAttribute("employees", employees);
        model.addAttribute("selectedDepartment", department);
        return "employeeView";
    }

    @PostMapping("/removeEmployee")
    public String removeEmployee(@SessionAttribute Department selectedDepartment, int employeeNo, Model model) {
        Employee currentEmployee = employeeRepository.getById(employeeNo);
        System.out.println(currentEmployee);
        employeeRepository.delete(currentEmployee);
        List<Employee> employees;
        if (selectedDepartment.getDeptName() == null) {
            System.out.println(selectedDepartment);
            employees = employeeRepository.findEmployeesByDepartment("Customer Service");
        } else {
            employees = employeeRepository.findEmployeesByDepartment(selectedDepartment.getDeptName());
        }
        model.addAttribute("employees", employees);
        return "employeeView";
    }

    @PostMapping("/createEmployee")
    public String addEmployee(Employee employee) {
        //TODO Spring-Validierungen, Save employee
        System.out.println(employee);
        return "employeeView";
    }

    @GetMapping
    public String showDesign() {
        return "employeeView";
    }

    @GetMapping("/form")
    public String showForm() {
        return "employeeForm";
    }
}

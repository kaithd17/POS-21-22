package at.kaindorf.controller;

import at.kaindorf.pojos.Department;
import at.kaindorf.pojos.Employee;
import at.kaindorf.repo.DepartmentRepository;
import at.kaindorf.repo.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequestMapping("employee")
@SessionAttributes({"selectedDepartment", "sorted"})
public class employeeController {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @ModelAttribute
    public void addAttributes(@ModelAttribute("selectedDepartment") Department selectedDepartment, Model model) {
        List<Department> departments = departmentRepository.findAll();
        if (selectedDepartment.getDeptName() == null) {
            departments.sort(Comparator.comparing(Department::getDeptName));
            departments.get(0).getEmployees().sort(Comparator.comparing(Employee::getLastname).thenComparing(Employee::getFirstname));
            model.addAttribute("employees", departments.get(0).getEmployees());
            model.addAttribute("selectedDepartment", departments.get(0));
            model.addAttribute("managerEmpno", departments.get(0).getDeptManager().getEmployeeNo());
        } else {
            Department department = departments.stream().filter(department1 -> department1.getDeptName().equals(selectedDepartment.getDeptName())).findFirst().get();
            model.addAttribute("employees", department.getEmployees());
            model.addAttribute("selectedDepartment", selectedDepartment);
            Department departmentManager = departmentRepository.getDepartmentByDeptName(selectedDepartment.getDeptName());
            model.addAttribute("managerEmpno", departmentManager.getDeptManager().getEmployeeNo());
        }
        //Get all sorted departmentNames
        model.addAttribute("departments", departments.stream().map(Department::getDeptName).collect(Collectors.toList()).stream().sorted().collect(Collectors.toList()));
        model.addAttribute("selectedEmployee", new Employee());
        model.addAttribute("newEmployee", new Employee());
    }

    @ModelAttribute("sorted")
    public Boolean sorted() {
        return Boolean.TRUE;
    }

    @ModelAttribute("sortText")
    public String sortText() {
        return "Sort DESC";
    }

    @PostMapping("/sortAddGetEmployees")
    public String sortAddGetEmployees(@SessionAttribute Department selectedDepartment, @SessionAttribute Boolean sorted, Model model, @RequestParam("buttonText") String buttonText) {
        List<Employee> employees;

        switch (buttonText) {
            case "sort":
                if (!sorted) {
                    employees = employeeRepository.findEmployeesByDepartmentSorted(selectedDepartment.getDeptName());
                    //update model
                    model.addAttribute("sorted", Boolean.TRUE);
                    model.addAttribute("sortText", "Sort DESC");
                } else {
                    employees = employeeRepository.findEmployeesByDepartment(selectedDepartment.getDeptName());
                    //update model
                    model.addAttribute("sorted", Boolean.FALSE);
                    model.addAttribute("sortText", "Sort ASC");
                }
                model.addAttribute("employees", employees);
                break;

            case "submit":
                employees = employeeRepository.findEmployeesByDepartmentSorted(selectedDepartment.getDeptName());

                //update model
                model.addAttribute("employees", employees);
                model.addAttribute("selectedDepartment", selectedDepartment);
                model.addAttribute("sorted", Boolean.TRUE);
                model.addAttribute("sortText", "Sort DESC");

                Department department = departmentRepository.getDepartmentByDeptName(selectedDepartment.getDeptName());
                model.addAttribute("managerEmpno", department.getDeptManager().getEmployeeNo());
                break;

            case "create":
                return "redirect:/employee/form";

        }
        return "employeeView";
    }

    @PostMapping("/removeEmployee")
    public String removeEmployee(@SessionAttribute Department selectedDepartment, int employeeNo, Model model, @SessionAttribute Boolean sorted) {
        Employee currentEmployee = employeeRepository.getById(employeeNo);
        employeeRepository.delete(currentEmployee);
        List<Employee> employees;

        employees = sortList(sorted, selectedDepartment, model);
        model.addAttribute("employees", employees);
        return "employeeView";
    }

    @PostMapping("/createEmployee")
    public String addEmployee(@Valid @ModelAttribute("newEmployee") Employee newEmployee, Errors errors, @SessionAttribute Department selectedDepartment, Model model, @SessionAttribute Boolean sorted) {
        //TODO Spring-Validierungen
        if (errors.hasErrors()){
            log.info(errors.getObjectName() + " " + errors.getAllErrors());
            return "employeeForm";
        }
        //To create a new empno
        int lastId = (employeeRepository.getLastEmpNo() + 1);
        newEmployee.setEmployeeNo(lastId);

        //Adding the employee in the right department
        Department department = departmentRepository.getDepartmentByDeptName(selectedDepartment.getDeptName());
        newEmployee.setDepartment(department);
        employeeRepository.saveAndFlush(newEmployee);

        //Get new EmployeeList
        List<Employee> employees = new ArrayList<>();
        employees = sortList(sorted, selectedDepartment, model);
        model.addAttribute("employees", employees);
        return "employeeView";
    }

    @GetMapping
    public String showDesign() {
        return "employeeView";
    }

    @GetMapping("/form")
    public String showForm(Model model) {
        return "employeeForm";
    }

    //Methods
    public List<Employee> sortList(boolean sorted, Department selectedDepartment, Model model) {
        List<Employee> employees;
        if (sorted) {
            model.addAttribute("sortText", "Sort DESC");
            return employees = employeeRepository.findEmployeesByDepartmentSorted(selectedDepartment.getDeptName());
        } else {
            model.addAttribute("sortText", "Sort ASC");
            return employees = employeeRepository.findEmployeesByDepartment(selectedDepartment.getDeptName());
        }
    }
}

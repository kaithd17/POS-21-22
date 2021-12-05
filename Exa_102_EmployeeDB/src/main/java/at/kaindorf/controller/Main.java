package at.kaindorf.controller;

import at.kaindorf.json.JSONAccess;
import at.kaindorf.pojos.Department;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Department> departments = JSONAccess.getEmployeeData();
        for (Department department: departments) {
            System.out.println(department.getDeptManager().getDepartment());
        }
    }
}

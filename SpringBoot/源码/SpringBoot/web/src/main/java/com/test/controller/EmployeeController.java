package com.test.controller;

import com.test.dao.DepartmentDao;
import com.test.dao.EmployeeDao;
import com.test.pojo.Department;
import com.test.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.yaml.snakeyaml.events.Event;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    @RequestMapping("/Employees")
    public String EmployeeLists(Model model){
        Collection<Employee> employees=employeeDao.getAll();
        model.addAttribute("employees",employees);
        return "list";
    }


    @GetMapping("/add")
    public String toAddEmployee(Model model){
        //获得所有部门信息
        Collection<Department> departments= departmentDao.getDepartmentMap();
        model.addAttribute("departments",departments);
        return "addEmployees";
    }

    @PostMapping("/add")
    public String AddEmployee(Employee employee){
        employeeDao.add(employee);//调用底层业务添加一个员工
        System.out.println(employee);
        return "redirect:Employees";
    }

    @GetMapping("/Employee/{id}")
    public String ToUpdateEmployee(@PathVariable("id") Integer id,Model model){
        Employee employee= employeeDao.getEmployeeByID(id);
        model.addAttribute("employee",employee);
        Collection<Department> departments= departmentDao.getDepartmentMap();
        model.addAttribute("departments",departments);
        return "updateEmployee";
    }


    @PostMapping("update")
    public String UpdateEmployee(Employee employee){
        employeeDao.add(employee);//调用底层业务保存修改一个员工 map重复就会被覆盖
        return "redirect:Employees";
    }

    @GetMapping("/DeleteEmployee/{id}")
    public String DeleteEmployee(@PathVariable("id") Integer id){
        employeeDao.Delete(id);
        return "redirect:/Employees";
    }
}

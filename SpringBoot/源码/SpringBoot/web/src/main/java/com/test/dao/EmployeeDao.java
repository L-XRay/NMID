package com.test.dao;

import com.test.pojo.Department;
import com.test.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeDao {
    //模拟数据库中的数据
    private static Map<Integer, Employee> employeeMap=null;
    @Autowired
    private DepartmentDao departmentDao;

    static {
        employeeMap=new HashMap<Integer,Employee>();//创建一个部门表

        employeeMap.put(1,new Employee(1,"JOJO",1,"3058",new Department(101,"ui")));
        employeeMap.put(2,new Employee(2,"Ray",1,"3058",new Department(102,"产品")));
        employeeMap.put(3,new Employee(3,"Three",0,"3058",new Department(103,"后端")));
        employeeMap.put(4,new Employee(4,"X",1,"3058",new Department(104,"前端")));
        employeeMap.put(5,new Employee(5,"Y",0,"3058",new Department(105,"硬件")));
    }

    //主键自增
    private static Integer initId=6;

    //增加一个员工
    public void add(Employee employee){
        if(employee.getID()==null){
            employee.setID(initId++);
        }
        employee.setDepartment(departmentDao.getDepartmentByID(employee.getDepartment().getID()));
        employeeMap.put(employee.getID(),employee);
    }

    //查询全部员工信息
    public Collection<Employee> getAll(){
        return employeeMap.values();
    }

    //通过ID查询员工信息
    public Employee getEmployeeByID(Integer ID){
        return employeeMap.get(ID);
    }

    //通过ID删除员工信息
    public Employee Delete(Integer ID){
        initId--;//每执行一次删除，自增id递减1位
        return employeeMap.remove(ID);
    }
}

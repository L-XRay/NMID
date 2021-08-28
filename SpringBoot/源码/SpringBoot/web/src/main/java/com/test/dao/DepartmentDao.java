package com.test.dao;

import com.test.pojo.Department;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class DepartmentDao {
    //模拟数据库中的数据
    private static Map<Integer,Department> departmentMap=null;
    static {
        departmentMap=new HashMap<Integer,Department>();//创建一个部门表

        departmentMap.put(101,new Department(101,"ui"));
        departmentMap.put(102,new Department(102,"产品"));
        departmentMap.put(103,new Department(103,"后端"));
        departmentMap.put(104,new Department(104,"前端"));
        departmentMap.put(105,new Department(105,"硬件"));
    }

    //获得所有部门信息
    public Collection<Department> getDepartmentMap(){
        return departmentMap.values();
    }

    //通过ID得到部门
    public Department getDepartmentByID(int ID){
        return departmentMap.get(ID);
    }
}

package com.depth.management.service.impl;

import com.depth.management.common.exception.ServiceException;
import com.depth.management.mapper.SalaryMapper;
import com.depth.management.model.Emp;
import com.depth.management.model.Salary;
import com.depth.management.model.SalaryHistory;
import com.depth.management.service.EmpService;
import com.depth.management.service.SalaryHistoryService;
import com.depth.management.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalaryServiceImpl implements SalaryService {

    @Autowired
    private SalaryMapper salaryMapper;

    @Autowired
    private EmpService empService;

    @Autowired
    private SalaryHistoryService salaryHistoryService;

    @Override
    public List<Salary> findListByDepartmentId(Long departmentId) {
        List<Emp> emps = empService.findByDepartmentId(departmentId);
        List<Long> ids = emps.stream().map(Emp::getId).collect(Collectors.toList());
        Example example = new Example(Salary.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("empId", ids);
        List<Salary> list = null;
        try {
            list = salaryMapper.selectByExample(example);
            list.forEach(item -> emps.forEach(v -> {
                if (v.getId().equals(item.getEmpId())) {
                    item.setEmp(v);
                }
            }));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
        return list;
    }

    @Override
    public List<Salary> findAll() {
        List<Salary> salaries = salaryMapper.selectAll();
        List<Emp> emps = empService.findAll();
        for (Salary salary : salaries) {
            for (Emp emp : emps) {
                if (emp.getId().equals(salary.getEmpId())) {
                    salary.setEmp(emp);
                }
            }
        }
        salaries.removeIf(item -> item.getEmp() == null);
        return salaries;
    }

    @Override
    public Salary findById(Long id) {
        Salary salary = new Salary();
        salary.setId(id);
        Salary one = null;
        try {
            one = salaryMapper.selectOne(salary);
            Emp emp = empService.findById(one.getEmpId());
            one.setEmp(emp);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
        return one;
    }

    @Override
    public Salary findByEmpId(Long empId) {
        Salary salary = new Salary();
        salary.setEmpId(empId);
        Emp emp;
        try {
            salary = salaryMapper.selectOne(salary);
            emp = empService.findById(empId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
        salary.setEmp(emp);
        return salary;
    }

    @Override
    public Salary save(Salary salary, String opa) {
        Date date = new Date();
        salary.setCreateUser(opa);
        salary.setCreateTime(date);
        salary.setUpdateTime(date);
        salary.setUpdateUser(opa);

        try {
            salaryMapper.insert(salary);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
        return salary;
    }

    @Override
    public void update(Salary salary, String opa) {
        Date date = new Date();
        salary.setUpdateTime(date);
        salary.setUpdateUser(opa);

        if (salary.getId() != null) {
            try {
                Salary key = salaryMapper.selectByPrimaryKey(salary.getId());
                SalaryHistory sh = new SalaryHistory();
                sh.setEmpId(salary.getEmpId());
                sh.setSalary(key.getBase());
                sh.setComment(salary.getComment());
                salaryHistoryService.save(sh, opa);

                salaryMapper.updateByPrimaryKeySelective(salary);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServiceException(e);
            }
        } else {
            throw new ServiceException("ID为空");
        }
    }
}

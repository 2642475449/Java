package com.imooc.jdbc.hrapp.entity;

import java.util.Date;

/**
 * 员工实体类
 */
public class Employee {
    /**
     * 1.具备默认构造函数
     * 2.属性私有
     * 3.存在getter与setter
     */

    private Integer eno;
    private String ename;
    private Float salary;
    private String dname;
    private Date hiredate;

    public Employee() {
    }

    public Employee(Integer eno, String ename, Float salary, String dname) {
        this.eno = eno;
        this.ename = ename;
        this.salary = salary;
        this.dname = dname;
    }

    public Integer getEno() {
        return eno;
    }

    public void setEno(Integer eno) {
        this.eno = eno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }
}

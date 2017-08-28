package com.jfsoft.queue.entity;

/**
 * 体检者
 */
public class PerCheckinfo {

    /**
     * 体检号
     */
    private String testno;

    /**
     * 档案号
     */
    private String recodeno;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 年龄单位
     */
    private String ageUnit;

    public String getTestno() {
        return testno;
    }

    public void setTestno(String testno) {
        this.testno = testno;
    }

    public String getRecodeno() {
        return recodeno;
    }

    public void setRecodeno(String recodeno) {
        this.recodeno = recodeno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAgeUnit() {
        return ageUnit;
    }

    public void setAgeUnit(String ageUnit) {
        this.ageUnit = ageUnit;
    }

}

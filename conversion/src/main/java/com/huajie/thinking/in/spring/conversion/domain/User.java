package com.huajie.thinking.in.spring.conversion.domain;


/**
 * @author ：xwf
 * @date ：Created in 2020\6\13 0013 16:45
 */
public class User {

    private Long id;

    private String name;

    private Integer age;

    private Company company;

    private String companyAsText;

    public String getCompanyAsText() {
        return companyAsText;
    }

    public void setCompanyAsText(String companyAsText) {
        this.companyAsText = companyAsText;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", company=" + company +
                ", companyAsText=" + companyAsText +
                '}';
    }
}

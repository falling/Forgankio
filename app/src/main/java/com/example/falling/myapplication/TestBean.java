package com.example.falling.myapplication;

/**
 * Created by falling on 16/5/8.
 */
public class TestBean {
    private String name = "falling";
    private String sex  = "男";
    private String age  =  "22";

    public TestBean(){}
    public TestBean(String age) {
        this.age = age;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}

package cn.ycm.springbootdemo.model;

/**
 * @author YUANCHENGMAN
 * @date 2020-11-24
 */
public class Student {
    private String name;
    private int age;
    private int gender;

    public Student(String name, int age, int gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
}

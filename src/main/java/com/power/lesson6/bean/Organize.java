package com.power.lesson6.bean;

/**
 * Created by shenli on 2017/1/26.
 */
public class Organize{

    private Long id;
    private String name;

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

    @Override
    public String toString() {
        return "Organize{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

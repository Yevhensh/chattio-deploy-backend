package com.chattio.dto;

import java.io.Serializable;
import java.util.Objects;

public class SampleDto implements Serializable {
    private String name;
    private int age;

    public SampleDto() {
    }

    public SampleDto(String name, int age) {
        this.name = name;
        this.age = age;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SampleDto sampleDto = (SampleDto) o;
        return Objects.equals(name, sampleDto.name) &&
                Objects.equals(age, sampleDto.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "SampleDto{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}

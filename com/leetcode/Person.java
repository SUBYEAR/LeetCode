
/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode;

/**
 * 功能描述
 *
 * @since 2020-04-07
 */
public class Person implements Comparable<Person> {
    private Integer age;

    private String name;

    public Person() {
    }

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Person o) {
        return name.compareTo(o.getName());
    }

    @Override
    public String toString() {
        return "Person{" + "age=" + age + ", name='" + name + '\'' + '}';
    }

    // @Override
    // public boolean equals(Object o) {
    // if (this == o) {
    // return true;
    // }
    // if (o == null || getClass() != o.getClass()) {
    // return false;
    // }
    //
    // Person person = (Person) o;
    //
    // if (!age.equals(person.age)) {
    // return false;
    // }
    // return name.equals(person.name);
    //
    // }
    //
    // @Override
    // public int hashCode() {
    // int result = age.hashCode();
    // result = 31 * result + name.hashCode();
    // return result;
    // }
}

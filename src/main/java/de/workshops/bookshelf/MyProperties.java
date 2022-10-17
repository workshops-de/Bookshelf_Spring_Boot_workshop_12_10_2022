package de.workshops.bookshelf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import java.util.Objects;

@Component
@ConfigurationProperties("my")
@Validated
public class MyProperties {
    private String name;
    private Integer age;
    @Email
    private String mail;

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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyProperties that = (MyProperties) o;
        return Objects.equals(name, that.name) && Objects.equals(age, that.age) && Objects.equals(mail, that.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, mail);
    }

    @Override
    public String toString() {
        return "MyProperties{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", mail='" + mail + '\'' +
                '}';
    }
}

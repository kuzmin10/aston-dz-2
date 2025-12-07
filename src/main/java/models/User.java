package models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private int age;
    private Timestamp created_at;

    public User() {
    }

    public User(String name, int age, String email) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.created_at = new Timestamp(System.currentTimeMillis());
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    @Override
    public String toString() {
        return "id: " + id + ", name: " + name + ", email: " + email + ", age: " + age + ", created at: " + created_at;
    }
}

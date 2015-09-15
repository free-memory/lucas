package iken.domain.object;


import org.json.JSONObject;

/**
 * Created by ken on 15/8/25.
 */
public class User {
    private int id;
    private String name;
    private int age;

    public User() {
    }

    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public User(JSONObject jsonObj) {
        for (String key : jsonObj.keySet()) {
            switch (key) {
                case "id":
                    id = (int) jsonObj.get("id");
                    break;
                case "name":
                    name = (String) jsonObj.get("name");
                    break;
                case "age":
                    age = (int) jsonObj.get("age");
                    break;
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    public String toString() {
        return "User{" +
                "ID='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

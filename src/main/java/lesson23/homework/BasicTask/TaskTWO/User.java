package lesson23.homework.BasicTask.TaskTWO;

public class User {

    private int id;
    private String name;
    private String email;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "\nUser:" + "\n" +
                "ID: " + this. id + "\n" +
                "Name:" + this.name + "\n" +
                "Email: " + this.email + "\n";
    }
}

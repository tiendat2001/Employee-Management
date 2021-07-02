
public class employee  {
    private String id;
    private String name;
    private String email;
    private double salary;
    private int age;

    public employee(){};

    public employee(String id, String name, String email, double salary, int age) {
        this.id=id;
        this.name=name;
        this.email = email;
        this.salary = salary;
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

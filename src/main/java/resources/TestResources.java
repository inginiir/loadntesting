package resources;

//@SuppressWarnings("UnusedDeclaration")
public class TestResources implements ResourceService {

    private String name;
    private int age;

    public TestResources(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public TestResources() {
        this.age = 0;
        this.name = "";
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

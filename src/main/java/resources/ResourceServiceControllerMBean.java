package resources;

@SuppressWarnings("UnusedDeclaration")
public interface ResourceServiceControllerMBean {

    String getName();

    int getAge();

    void setName(String name);

    void setAge(int age);
}

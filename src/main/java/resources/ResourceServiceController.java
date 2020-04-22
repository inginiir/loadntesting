package resources;

public class ResourceServiceController implements ResourceServiceControllerMBean {

    private final ResourceService resourceService;

    public ResourceServiceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @Override
    public String getName() {
        return resourceService.getName();
    }

    @Override
    public int getAge() {
        return resourceService.getAge();
    }

    @Override
    public void setName(String name) {
        resourceService.setName(name);
    }

    @Override
    public void setAge(int age) {
        resourceService.setAge(age);
    }
}

package accountServer;

@SuppressWarnings("UnusedDeclaration")
public interface AccountServerControllerMBean {

    int getUser();

    int getUsersLimit();

    void setUsersLimit(int userLimit);

}

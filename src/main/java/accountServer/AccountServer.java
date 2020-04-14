package accountServer;

public interface AccountServer {

    void addNewUser();

    void removeUser();

    int getUsersLimit();

    void setUsersLimit(int userLimit);

    int getUsersCount();
}

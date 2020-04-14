package accountServer;

public class AccountServerImpl implements AccountServer {

    private int usersCount;
    private int usersLimit;

    public AccountServerImpl(int userLimit) {
        this.usersLimit = userLimit;
    }

    @Override
    public void addNewUser() {
        usersCount++;
    }

    @Override
    public void removeUser() {
        usersCount--;
    }

    @Override
    public int getUsersLimit() {
        return usersLimit;
    }

    @Override
    public void setUsersLimit(int usersLimit) {
        this.usersLimit = usersLimit;
    }

    @Override
    public int getUsersCount() {
        return usersCount;
    }
}

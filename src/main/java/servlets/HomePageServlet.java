package servlets;

import accountServer.AccountServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomePageServlet extends HttpServlet {

    static final Logger LOGGER = LogManager.getLogger(HomePageServlet.class.getName());
    public static final String PAGE_URL = "/home";
    private final AccountServer accountServer;

    public HomePageServlet(AccountServer accountServer) {
        this.accountServer = accountServer;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");

        String remove = req.getParameter("remove");

        if (remove != null) {
            accountServer.removeUser();
            resp.getWriter().print("Goodbye!");
            resp.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        int limit = accountServer.getUsersLimit();
        int count = accountServer.getUsersCount();

        LOGGER.info("Limit: {}. Count: {}",  limit, count);

        if (limit > count) {
            LOGGER.info("User pass");
            accountServer.addNewUser();
            resp.getWriter().print("Welcome!");
            resp.setStatus(HttpServletResponse.SC_OK);
        }
        else {
            LOGGER.info("User rejected");
            resp.getWriter().print("Server is full");
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}

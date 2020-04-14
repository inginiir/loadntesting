package servlets;

import accountServer.AccountServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminServlet extends HttpServlet {

    static final Logger LOGGER = LogManager.getLogger(HomePageServlet.class.getName());
    public static final String ADMIN_URL = "/admin";
    private final AccountServer accountServer;

    public AdminServlet(AccountServer accountServer) {
        this.accountServer = accountServer;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");
        int limit = accountServer.getUsersLimit();
        resp.getWriter().print(limit);
        resp.setStatus(HttpServletResponse.SC_OK);

    }

}

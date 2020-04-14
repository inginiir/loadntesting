package servlets;

import accountServer.AccountServer;
import org.junit.Assert;
import org.junit.Test;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.mockito.Mockito.*;

public class HomePageServletTest {

    private AccountServer accountServer = mock(AccountServer.class);

    private HttpServletResponse getMockedResponse(StringWriter stringWriter) throws IOException {
        HttpServletResponse response = mock(HttpServletResponse.class);
        final PrintWriter writer = new PrintWriter(stringWriter);

        when(response.getWriter()).thenReturn(writer);
        return response;
    }

    private HttpServletRequest getMockedRequest(String url) {
        HttpSession session = mock(HttpSession.class);
        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getSession()).thenReturn(session);
        when(request.getPathInfo()).thenReturn(url);
        return request;
    }

    @Test
    public void testRemove() throws Exception {
        final StringWriter writer = new StringWriter();
        HttpServletRequest request = getMockedRequest(HomePageServlet.PAGE_URL);
        HttpServletResponse response = getMockedResponse(writer);

        when(request.getParameter("remove")).thenReturn("");

        HomePageServlet servlet = new HomePageServlet(accountServer);

        servlet.doGet(request, response);

        Assert.assertEquals("Goodbye!", writer.toString().trim());
        verify(accountServer, times(1)).removeUser();
    }
}

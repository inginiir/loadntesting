package servlets;

import resources.ResourceService;
import resources.TestResources;
import resources.sax.ReadXMLFileSAX;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResourceServlet extends HttpServlet {

    public static final String RESOURCE_URL = "/resources";
    private static String path;
    private final ResourceService resourceService;

    public ResourceServlet(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().print("Hello");
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        path = req.getParameter("path");
        resp.setStatus(HttpServletResponse.SC_OK);
        ResourceService resourceService1 = (TestResources) ReadXMLFileSAX.readXML(path);
        if (resourceService1 != null) {
            resourceService.setAge(resourceService1.getAge());
            resourceService.setName(resourceService1.getName());
        }
        resp.getWriter().print(resourceService.getName() + " : " + resourceService.getAge());
    }
}

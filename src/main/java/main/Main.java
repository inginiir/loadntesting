package main;


import accountServer.AccountServer;
import accountServer.AccountServerController;
import accountServer.AccountServerControllerMBean;
import accountServer.AccountServerImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import resources.ResourceService;
import resources.ResourceServiceController;
import resources.ResourceServiceControllerMBean;
import resources.TestResources;
import servlets.AdminServlet;
import servlets.HomePageServlet;
import servlets.ResourceServlet;

import javax.management.*;
import java.lang.management.ManagementFactory;

public class Main {

    static final Logger LOGGER = LogManager.getLogger(Main.class.getName());

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            LOGGER.error("Use port as the first argument");
            System.exit(1);
        }

        int port = Integer.parseInt(args[0]);

        LOGGER.info("Starting at http://127.0.0.1:" + port);

        AccountServer accountServer = new AccountServerImpl(10);
        ResourceService resourceService = new TestResources();

        AccountServerControllerMBean mBean = new AccountServerController(accountServer);
        ResourceServiceControllerMBean resourceBean = new ResourceServiceController(resourceService);
        MBeanServer beanServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName accountServerJMX = new ObjectName("ServerManager:type=AccountServerController");
        ObjectName userLimitJMX = new ObjectName("Admin:type=AccountServerController.usersLimit");
        ObjectName resourceServiceJMX = new ObjectName("Admin:type=ResourceServiceController");
        beanServer.registerMBean(mBean, accountServerJMX);
        beanServer.registerMBean(mBean, userLimitJMX);
        beanServer.registerMBean(resourceBean, resourceServiceJMX);

        Server server = new Server(port);
        ServletContextHandler contextHandler =  new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.addServlet(new ServletHolder(new HomePageServlet(accountServer)), HomePageServlet.PAGE_URL);
        contextHandler.addServlet(new ServletHolder(new AdminServlet(accountServer)), AdminServlet.ADMIN_URL);
        contextHandler.addServlet(new ServletHolder(new ResourceServlet(resourceService)), ResourceServlet.RESOURCE_URL);

        ResourceHandler handler = new ResourceHandler();
        handler.setResourceBase("static");
        handler.setDirectoriesListed(true);

        HandlerList handlerList = new HandlerList();
        handlerList.setHandlers(new Handler[]{handler, contextHandler});
        server.setHandler(handlerList);

        server.start();
        LOGGER.info("Server started");
        System.out.println("Server started");
        server.join();

    }
}

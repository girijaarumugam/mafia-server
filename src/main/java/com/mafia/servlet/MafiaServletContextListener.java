package com.mafia.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * created by girija-4135 on 24/12/19
 */
public class MafiaServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("********************** Inside Context Initialized ******************************");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("**************************** Inside Context Destroyed ****************************");
    }
}

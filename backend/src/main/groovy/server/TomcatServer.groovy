package server

import org.apache.catalina.LifecycleException
import org.apache.catalina.startup.Tomcat

class TomcatServer implements Runnable {
    @Override
    void run() throws LifecycleException {
        def tomcat = new Tomcat()
        tomcat.setPort(8080)
        tomcat.getConnector()
        tomcat.setBaseDir("build/tomcat")

        def ctx = tomcat.addContext("/api", new File(".").absolutePath)

        Tomcat.addServlet(ctx, "HelloServlet", new HelloServlet())
        ctx.addServletMappingDecoded("/hello", "HelloServlet")

        Tomcat.addServlet(ctx, "TimeServlet", new TimeServlet())
        ctx.addServletMappingDecoded("/time", "TimeServlet")

        tomcat.start()
        println "Tomcat server started on http://localhost:8080"

        tomcat.getServer().await()
    }
}

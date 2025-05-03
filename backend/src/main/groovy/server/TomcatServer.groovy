package server

import controller.CandidatoController

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

        Tomcat.addServlet(ctx, "CandidatoController", new CandidatoController())
        ctx.addServletMappingDecoded("/candidatos/*", "CandidatoController")

        tomcat.start()
        println "Tomcat server started on http://localhost:8080"

        tomcat.getServer().await()
    }
}

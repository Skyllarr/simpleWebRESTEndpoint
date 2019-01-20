package demo.stackoverflow.health;


import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("ping")
@Stateless
public class Ping {

    @Resource
    private SessionContext sessionContext;
    
    @GET
    @RolesAllowed("Admin")
    public String ping() {
        return sessionContext != null ? "OK " + sessionContext.getCallerPrincipal().getName() : "OK";
    }
    
}

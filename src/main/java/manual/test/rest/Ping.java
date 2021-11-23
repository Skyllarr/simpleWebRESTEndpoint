package manual.test.rest;


import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJBContext;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("ping")
@Stateless
public class Ping {

    @Resource
    private SessionContext sessionContext;

    @Resource
    private EJBContext ejbContext;


    @GET
    @RolesAllowed("Admin")
    public String ping() {

        return sessionContext == null ? "No session context! " :  "Caller principal from session context is: " + sessionContext.getCallerPrincipal().getName() + "." +
                "\n Caller principal from ejb context is: " + ejbContext.getCallerPrincipal();
    }

}

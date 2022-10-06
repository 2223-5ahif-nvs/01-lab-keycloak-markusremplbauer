package at.htl;

import io.quarkus.security.identity.SecurityIdentity;
import org.jboss.resteasy.reactive.NoCache;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/api/users")
public class UserResource {
    @Inject
    SecurityIdentity identity;

    @GET
    @Path("/me")
    @NoCache
    public User me() {
        return new User(identity);
    }

    @GET
    @Path("/admin")
    @NoCache
    @RolesAllowed({"admin"})
    public String admin(){
        return "granted";
    }

    public static class User {

        private final String userName;

        User(SecurityIdentity identity) {
            this.userName = identity.getPrincipal().getName();
        }

        public String getUserName() {
            return userName;
        }
    }
}
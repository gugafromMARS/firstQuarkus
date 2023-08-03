package academy.mindswap.resource;

import academy.mindswap.model.User;
import academy.mindswap.service.UserServiceImp;
import com.oracle.svm.core.annotate.Delete;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;


@Path("/users")
public class UserResource {

    @Inject
    UserServiceImp userServiceImp;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> get() {
        return userServiceImp.get();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{email}")
    public User get(@PathParam("email") String email){
        return userServiceImp.get(email);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public User create(User user){
        return userServiceImp.create(user);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    @Path("/bulk")
    public List<User> create(List<User> userList){
        return userServiceImp.create(userList);
    }

    @Delete
    @Path("/{email}")
    @Transactional
    public void delete(@PathParam("email") String email){
        userServiceImp.delete(email);
    }

    @PUT
    @Path("/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public User put(@PathParam("email") String email, User user){
        return userServiceImp.put(email, user);
    }


}

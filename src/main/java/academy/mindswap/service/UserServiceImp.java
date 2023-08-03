package academy.mindswap.service;


import academy.mindswap.model.User;
import academy.mindswap.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;

import java.util.List;

@ApplicationScoped
public class UserServiceImp implements UserService {
    @Inject
    UserRepository userRepository;


    @Override
    public List<User> get() {
        return userRepository.findAll().list();
    }

    @Override
    public User get(String email){
        return userRepository.find("email", email).firstResultOptional()
                .orElseThrow(() -> new WebApplicationException("User not found", 404));
    }

    @Override
    public User create(User user) {
        userRepository.persist(user);
        return user;
    }

    @Override
    public List<User> create(List<User> userList) {
        userRepository.persist(userList);
        return userList;
    }

    @Override
    public void delete(String email) {
       User user = get(email);
       userRepository.delete(user);
    }

    @Override
    public User put(String email, User user) {
        User u = get(email);
        if(!user.getId().equals(u.getId())){
            throw new WebApplicationException("Id can't be changed!");
        }
        if(!user.getEmail().equals(u.getEmail())){
            throw new WebApplicationException("Email can't be changed!");
        }

        u.setName(user.getName());
        userRepository.persist(u);
        return u;
    }
}

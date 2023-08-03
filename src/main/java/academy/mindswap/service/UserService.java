package academy.mindswap.service;

import academy.mindswap.model.User;

import java.util.List;

public interface UserService {
    List<User> get();

    User get(String email);

    User create(User user);

    List<User> create(List<User> userList);

    void delete(String email);

    User put(String email, User user);
}

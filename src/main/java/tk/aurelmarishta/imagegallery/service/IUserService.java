package tk.aurelmarishta.imagegallery.service;

import java.util.List;

import tk.aurelmarishta.imagegallery.model.User;

public interface IUserService {
    List<User> findAll();

    void save(User user);

    User getUser(String username);

    void deleteUser(User user);

    User findUserByEmail(String email);

}

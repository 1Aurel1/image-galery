package tk.aurelmarishta.imagegallery.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import tk.aurelmarishta.imagegallery.model.User;

public interface UserRepository extends JpaRepository<User, String> {

    public User findByEmailIgnoreCase(String email);

    @Override
    void delete(User user);
}

package tk.aurelmarishta.imagegallery.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.aurelmarishta.imagegallery.dao.AuthorityRepository;
import tk.aurelmarishta.imagegallery.dao.ConfirmationTokenRepository;
import tk.aurelmarishta.imagegallery.dao.UserRepository;
import tk.aurelmarishta.imagegallery.model.Authority;
import tk.aurelmarishta.imagegallery.model.User;

@Service
@Transactional
public class UserService implements IUserService {

    AuthorityRepository authDao;
    UserRepository userDao;
    ConfirmationTokenRepository confToken;

    @Autowired
    public UserService(UserRepository theUserDao, AuthorityRepository theAuthDao, ConfirmationTokenRepository theConfToken) {
        userDao = theUserDao;
        authDao = theAuthDao;
        confToken = theConfToken;
    }

    @Override
    public void save(User user) {
        userDao.save(user);
        Authority auth = new Authority(user.getUsername(), "ROLE_USER");
        authDao.save(auth);

    }

    @Override
    public void deleteUser(User user) {
        // TODO Auto-generated method stub

    }

    @Override
    public User findUserByEmail(String email) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }


    @Override
    public User getUser(String username) {


        return userDao.getOne(username);
    }

    public User findByEmailIgnoreCase(String email) {
        // TODO Auto-generated method stub
        return userDao.findByEmailIgnoreCase(email);
    }
}

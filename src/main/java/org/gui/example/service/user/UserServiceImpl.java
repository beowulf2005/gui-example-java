package org.gui.example.service.user;

import org.gui.example.ann.IndexUpdate;
import org.gui.example.entity.user.TUser;
import org.gui.example.repository.user.UserRepository;
import org.gui.example.service.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.IOException;

/**
 * @author gui
 * 
 */
@Component
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Inject
    private UserRepository userRepository;

    private UserServiceImpl() {

    }
    
    private TUser userToTuser(TUser tuser, User user){
    	tuser.setUsername(user.getUsername());
    	tuser.setBirth(user.getBirth());
    	tuser.setEmail(user.getEmail());
    	tuser.setGender(user.getGender());
    	return tuser;
    }
    
    private User tuserToUser(User  user, TUser tuser){
    	user.setUsername(tuser.getUsername());
    	user.setBirth(tuser.getBirth());
    	user.setEmail(tuser.getEmail());
    	user.setGender(tuser.getGender());
    	return user;
    }

    // private methods
    @Override
    public User findUser(long id) {
        final TUser u = userRepository.findById(id);
        return tuserToUser(new User(), u);
    }

    @Override
    public User findUserByName(String userName) {
    	final TUser u = userRepository.findByUsername(userName);
        return tuserToUser(new User(), u);
    }

    @Override
    public User findUserByNameOrEmail(String username, String email) {
    	final TUser u =  userRepository.findByNameOrEmail(username, email);
        return tuserToUser(new User(), u);
    }

    @Override
    @IndexUpdate(User.class)
    public void updateUser(User user) throws IOException {
    	final TUser u = userToTuser(new TUser(), user);
        this.userRepository.save(u);
    }

}

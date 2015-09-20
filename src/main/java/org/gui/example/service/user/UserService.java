package org.gui.example.service.user;

import org.gui.example.entity.user.TUser;
import org.gui.example.service.dto.User;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface UserService {

    User findUser(long id) throws IOException;

    void updateUser(User user) throws IOException;

    User findUserByName(String userName);

    User findUserByNameOrEmail(String username, String email);

}
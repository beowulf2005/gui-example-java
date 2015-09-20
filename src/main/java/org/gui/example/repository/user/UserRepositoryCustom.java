package org.gui.example.repository.user;

import java.util.List;

import org.gui.example.entity.user.TUser;

/**
 * Basic services for entity "user"
 * 
 * @author gui
 * 
 */
public interface UserRepositoryCustom {

    public List<TUser> seekUserByType(int type);

    public String findNameById(long userId);

    public long findIdByEmail(String email);

}

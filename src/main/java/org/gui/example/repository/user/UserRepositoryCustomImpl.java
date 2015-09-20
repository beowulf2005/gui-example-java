package org.gui.example.repository.user;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.gui.example.entity.user.TUser;

/**
 * Basic services for entity "user"
 * 
 * @author gui
 * 
 */
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public String findNameById(long userId) {
        String sql = "select username from t_user where id=:userId";
        return (String) em.createNativeQuery(sql)
                .setParameter("userId", userId).getSingleResult();
    }

    @Override
    public long findIdByEmail(String email) {
        String sql = "select id from t_user where email=:email";
        return (long) em.createNativeQuery(sql)
                .setParameter("email", email).getSingleResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<TUser> seekUserByType(int type) {
        return em.createNativeQuery("select * from t_user where type=:type", TUser.class)
                .setParameter("type", type).getResultList();
    }
}

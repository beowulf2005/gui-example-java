package org.gui.example.repository.user;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import org.gui.example.entity.user.TUser;

/**
 * Basic services for entity "user"
 * 
 * @author gui
 * 
 */
@Repository
@Transactional
public interface UserRepository extends CrudRepository<TUser, Long>, UserRepositoryCustom {

    @Query(value = "select * from t_user where username = :username and row_state = 0", nativeQuery = true)
    TUser findByUsername(@Param("username") String username);

    @Query(value = "select * from t_user where id = :id and row_state = 0", nativeQuery = true)
    TUser findById(@Param("id") Long id);

    @Query(value = "select * from t_user where ( username = :username or email = :email ) and row_state = 0", nativeQuery = true)
    TUser findByNameOrEmail(@Param("username") String username, @Param("email") String email);

    /**
     * 根据邮箱名模糊查询用户信息
     * 
     * @param email
     * @author lijingjing
     * */
    @Query(value = "select * from t_user where email = :email and row_state = 0", nativeQuery = true)
    TUser findByEmail(@Param("email") String email);

    @Query(
            value = "select * from t_user where row_state<1 and (username like %:param% or email like %:param%) limit 5 offset 0",
            nativeQuery = true)
    List<TUser> findUserByRowState(@Param("param") String param);

    @Query(
            value = "select * from t_user where row_state<1 and (username like %:param% or email like %:param%) limit :limit offset :offset",
            nativeQuery = true)
    List<TUser> findlikeUsername(@Param("param") String param, @Param("limit") int limit, @Param("offset") int offset);

    @Query(value = "select count(*) from t_user where row_state<1 and (username like %:param% or email like %:param%)",
            nativeQuery = true)
    int count(@Param("param") String param);

    @Query(value = "update t_user set row_state = 0 where id=:userId", nativeQuery = true)
    void activateUser(@Param("userId") long userId);

    @Query(value = "select * from t_user where id = :id and row_state = 1", nativeQuery = true)
    TUser findInactiveUserById(@Param("id") long id);
}

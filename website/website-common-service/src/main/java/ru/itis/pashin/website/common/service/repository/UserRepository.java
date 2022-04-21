package ru.itis.pashin.website.common.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.itis.pashin.website.common.model.user.entity.User;

import java.util.Optional;
import java.util.UUID;

/**
 * @author <a href="mailto:ruslan.pashin@waveaccess.ru">Ruslan Pashin</a>
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByGuid(UUID guid);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE users.user set is_blocked = true where id = :id")
    void blockUser(@Param("id") Long id);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE users.user set is_blocked = false where id = :id")
    void unblockUser(@Param("id") Long id);
}

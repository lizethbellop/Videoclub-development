package org.thevault.videoclubAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.thevault.videoclubAPI.model.User;
import org.thevault.videoclubAPI.model.dto.UserSessionDTO;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    @Query("SELECT new org.thevault.videoclubAPI.model.dto.UserSessionDTO(c.idClient, u.username, c.firstName, u.profilePicture, org.thevault.videoclubAPI.model.enumerations.UserType.CLIENT, null, u.email) FROM Client c JOIN c.user u WHERE u.username = :username")
    Optional<UserSessionDTO> findClientSessionByUsername(@Param("username") String username);

    @Query("SELECT new org.thevault.videoclubAPI.model.dto.UserSessionDTO(e.idEmployee, u.username, e.firstName, u.profilePicture, org.thevault.videoclubAPI.model.enumerations.UserType.EMPLOYEE, e.role, u.email) FROM Employee e JOIN e.user u WHERE u.username = :username")
    Optional<UserSessionDTO> findEmployeeSessionByUsername(@Param("username") String username);
}

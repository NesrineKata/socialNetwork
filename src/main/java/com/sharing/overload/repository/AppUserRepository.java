package com.sharing.overload.repository;

import com.sharing.overload.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    boolean existsByUsername(String userName);
    AppUser findAppUserByUsername(String userName);
    AppUser findAppUserById(long id);
}

package com.sharing.overload.repository;

import com.sharing.overload.entity.AppUserBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserBoardRepository extends JpaRepository<AppUserBoard, Long> {

    AppUserBoard findAppUserBoardByAppUser_Username(String username);
}

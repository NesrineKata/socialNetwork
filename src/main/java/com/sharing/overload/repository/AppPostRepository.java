package com.sharing.overload.repository;

import com.sharing.overload.entity.AppPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppPostRepository extends JpaRepository<AppPost, Long> {
    List<AppPost> findAppPostByUsername(String username);
    AppPost findAppPostById(long id);
}

package com.sharing.overload.repository;

import com.sharing.overload.entity.AppPostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppPostCommentRepository extends JpaRepository<AppPostComment, Long> {
    List<AppPostComment> findAllByAppPostId(long id);
}

package com.sharing.overload.service;

import com.sharing.overload.entity.AppPostComment;
import com.sharing.overload.repository.AppPostCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppPostCommentService {

    @Autowired
    private AppPostCommentRepository repository;

    public List<AppPostComment> findAllByAppPostId(long id) {
        return repository.findAllByAppPostId(id);
    }

    public void save(AppPostComment comment) {
        repository.save(comment);
    }
}

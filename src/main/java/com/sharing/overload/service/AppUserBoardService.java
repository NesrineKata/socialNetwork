package com.sharing.overload.service;

import com.sharing.overload.entity.AppUserBoard;
import com.sharing.overload.repository.AppUserBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppUserBoardService {

    @Autowired
    private AppUserBoardRepository repository;

    public void save(AppUserBoard board) {
        repository.save(board);
    }

    public AppUserBoard findBoardByUsername(String username) {
        return repository.findAppUserBoardByAppUser_Username(username);
    }
}

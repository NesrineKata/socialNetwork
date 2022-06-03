package com.sharing.overload.service;

import com.sharing.overload.entity.AppUser;
import com.sharing.overload.entity.AppUserBoard;
import com.sharing.overload.repository.AppUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppUserService {

    private final Logger logger = LoggerFactory.getLogger(AppUserService.class);

    @Autowired
    private AppUserRepository repository;

    @Autowired
    private AppUserBoardService boardService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void save(AppUser appUser) {
        repository.save(appUser);
    }

    public void addNewRegularUser(String userName, String password) {
        if (repository.existsByUsername(userName)) {
            logger.info("Tried to add user with existing username!");
            return;
        }

        String encryptedPassword = passwordEncoder.encode(password);

        AppUser user = new AppUser(AppUser.Role.REGULAR_USER, userName);
        user.setPassword(encryptedPassword);

        AppUserBoard board = new AppUserBoard();
        board.setHeader("Tablica użytkownika " + user.getUsername());
        boardService.save(board);
        user.setAppUserBoard(board);
        repository.save(user);
    }

    public List<AppUser> findAppUserContaining(String searchQuery) {
        List<AppUser> result = new ArrayList<>();
        for(AppUser appUser : repository.findAll()) {
            if(appUser.getUsername().contains(searchQuery))
                result.add(appUser);
        }
        return result;
    }

    public AppUser findAppUserByUsername(String userName) {
        return repository.findAppUserByUsername(userName);
    }

    public List<AppUser> getAllUsers() {
        return repository.findAll();
    }

    public AppUser findByUsername(String currentUserName) {
        return repository.findAppUserByUsername(currentUserName);
    }

    public AppUser findAppUserById(long id) {
        return repository.findAppUserById(id);
    }
}

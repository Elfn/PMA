package com.pma.app.services;

import com.pma.app.dao.ProjectRepository;
import com.pma.app.dao.UserAccountRepository;
import com.pma.app.entities.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Elimane on May, 2020, at 20:45
 */
@Service
public class UserAccountService implements UserAccountRepository {

    @Qualifier("userAccountRepository")
    @Autowired
    private UserAccountRepository userRepo;
    
    @Override
    public <S extends UserAccount> S save(S s) {
        return userRepo.save(s);
    }

    @Override
    public <S extends UserAccount> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<UserAccount> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<UserAccount> findAll() {
        return null;
    }

    @Override
    public Iterable<UserAccount> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(UserAccount userAccount) {

    }

    @Override
    public void deleteAll(Iterable<? extends UserAccount> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}

package com.pma.app.dao;

import com.pma.app.entities.Project;
import com.pma.app.entities.UserAccount;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Elimane on May, 2020, at 20:44
 */
public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {

}

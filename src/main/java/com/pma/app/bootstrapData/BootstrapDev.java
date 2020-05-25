package com.pma.app.bootstrapData;

import com.pma.app.dao.EmployeeRepository;
import com.pma.app.dao.ProjectRepository;
import com.pma.app.dao.UserAccountRepository;
import com.pma.app.entities.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by Elimane on May, 2020, at 00:53
 */
@Component
public class BootstrapDev implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;
    private final UserAccountRepository userAccountRepository;

    @Value("${admin.password}")
    private String pwd;

    public BootstrapDev(EmployeeRepository employeeRepository, ProjectRepository projectRepository, UserAccountRepository userAccountRepository) {
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
            this.loadData();
    }

    public void loadData()
    {


        UserAccount adminUser = new UserAccount();
        adminUser.setPassword(bCryptPasswordEncoder.encode(pwd));
        adminUser.setEmail("admin@gmail.com");
        adminUser.setEnabled(true);
        adminUser.setRole("ROLE_ADMIN");
        adminUser.setUsername("admin");

        userAccountRepository.save(adminUser);
    }
}

package com.pma.app.bootstrapData;

import com.pma.app.dao.EmployeeRepository;
import com.pma.app.dao.ProjectRepository;
import com.pma.app.dao.UserAccountRepository;
import com.pma.app.entities.Employee;
import com.pma.app.entities.Project;
import com.pma.app.entities.UserAccount;
import com.pma.app.services.ProjectEmployeeService;
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

    @Autowired
    private ProjectEmployeeService projectEmployeeService;

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

//------------------------------------ADMIN DEFINITION---------------------------
        UserAccount adminUser = new UserAccount();
        adminUser.setPassword(bCryptPasswordEncoder.encode(pwd));
        adminUser.setEmail("admin@gmail.com");
        adminUser.setEnabled(true);
        adminUser.setRole("ROLE_ADMIN");
        adminUser.setUsername("admin");

        userAccountRepository.save(adminUser);

//------------------------------------INSERT EMPLOYEES------------------------------
    Employee e1 = new Employee("John","Warton","warton@gmail.com","SENIOR");
    Employee e2 = new Employee("Mike","Lanister","lanister@gmail.com","INTERMEDIARY");
    Employee e3 = new Employee("Steve","Reeves","Reeves@gmail.com","JUNIOR");
    Employee e4 = new Employee("Ronald","Connor","connor@gmail.com","JUNIOR");
    Employee e5 = new Employee("Jim","Salvator","Sal@gmail.com","SENIOR");
    Employee e6 = new Employee("Peter","Henley","henley@gmail.com","INTERMEDIARY");
    Employee e7 = new Employee("Richard","Carson","carson@gmail.com","INTERMEDIARY");
    Employee e8 = new Employee("Honor","Miles","miles@gmail.com","JUNIOR");
    Employee e9 = new Employee("Tony","Roggers","roggers@gmail.com","SENIOR");

    employeeRepository.save(e1);
    employeeRepository.save(e2);
    employeeRepository.save(e3);
    employeeRepository.save(e4);
    employeeRepository.save(e5);
    employeeRepository.save(e6);
    employeeRepository.save(e7);
    employeeRepository.save(e8);
    employeeRepository.save(e9);

//------------------------------------INSERT PROJECTS------------------------------
        Project p1 = new Project("Large Production Deploy","NOTSTARTED","This requires all hands on deck for the final deployment of the software into production");
        Project p2 = new Project("New Employee Budget","COMPLETED","Decide on a new employee bonus budget for the year and figureout who will be promoted");
        Project p3 = new Project("Office Reconstruction","INPROGRESS","The office building in Monroe has been damaged due to hurricane in the region. This needs to be reconstructed");
        Project p4 = new Project("Improve Intranet Security","INPROGRESS","With the recent data hack, the office security needs to be improved and proper security team needs to be hired for implementation");

        projectRepository.save(p1);
        projectRepository.save(p2);
        projectRepository.save(p3);
        projectRepository.save(p4);

    //------------------------------------INSERT PROJECT_EMPLOYEE_RELATION------------------------------

        projectEmployeeService.assignProjectToEmployee(String.valueOf(e1.getEmployee_id()),String.valueOf(p1.getProject_id()));
        projectEmployeeService.assignProjectToEmployee(String.valueOf(e1.getEmployee_id()),String.valueOf(p2.getProject_id()));
        projectEmployeeService.assignProjectToEmployee(String.valueOf(e1.getEmployee_id()),String.valueOf(p3.getProject_id()));
        projectEmployeeService.assignProjectToEmployee(String.valueOf(e3.getEmployee_id()),String.valueOf(p1.getProject_id()));
        projectEmployeeService.assignProjectToEmployee(String.valueOf(e6.getEmployee_id()),String.valueOf(p3.getProject_id()));
        projectEmployeeService.assignProjectToEmployee(String.valueOf(e6.getEmployee_id()),String.valueOf(p4.getProject_id()));

//      p1.getEmployees().add(e1);
//      p2.getEmployees().add(e1);
//      p3.getEmployees().add(e1);
//      p1.getEmployees().add(e3);
//      p1.getEmployees().add(e1);
//      p1.getEmployees().add(e1);
//      p1.getEmployees().add(e1);
//      p1.getEmployees().add(e1);
    }


}

package demo;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import demo.entity.Company;
import demo.entity.Employee;
import demo.entity.Vacation;
import demo.repository.CompanyRepository;
import demo.repository.EmployeeRepository;
import demo.repository.VacationRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.yaml.snakeyaml.nodes.Node;

@SpringBootApplication
@EnableJdbcRepositories
public class DemoApplication {

  private JTree tree;

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);

    CompanyRepository companyRepository = context.getBean(CompanyRepository.class);
    EmployeeRepository employeeRepository = context.getBean(EmployeeRepository.class);
    VacationRepository vacationRepository = context.getBean(VacationRepository.class);

    Map<Company, Map<Employee, List<Vacation>>> result = findAllByCompany(companyRepository, employeeRepository, vacationRepository);

    System.out.println(result);
    System.setProperty("java.awt.headless", "false");
    var jFrame = new JFrame();
    JTree jTree = new JTree();
    jFrame.add(jTree);
    DefaultMutableTreeNode root = new DefaultMutableTreeNode("database");
    DefaultTreeModel model = new DefaultTreeModel(root);
    for (Map.Entry<Company, Map<Employee, List<Vacation>>> company : result.entrySet()) {
      DefaultMutableTreeNode oneCompany = new DefaultMutableTreeNode(company.getKey());
      if (company.getValue() != null) {
        for (Map.Entry<Employee, List<Vacation>> emp : company.getValue().entrySet()) {
          DefaultMutableTreeNode oneEmployee = new DefaultMutableTreeNode(emp.getKey());
          oneCompany.add(oneEmployee);
          if (emp.getValue() != null) {
            for(Vacation vacation: emp.getValue()){
              DefaultMutableTreeNode oneVacation = new DefaultMutableTreeNode(vacation);
              oneEmployee.add(oneVacation);
            }
          }
        }
      }
      root.add(oneCompany);
    }
    jTree.setModel(model);
    jFrame.pack();
    jFrame.setVisible(true);
  }

  private static Map<Company, Map<Employee, List<Vacation>>> findAllByCompany(CompanyRepository companyRepository, EmployeeRepository employeeRepository,
                                                                        VacationRepository vacationRepository) {
    return StreamSupport.stream(companyRepository.findAll().spliterator(), false)
      .map(company -> Map.entry(company,
        findVacationsByEmployee(employeeRepository, vacationRepository, company)))
      .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
  }

  private static Map<Employee, List<Vacation>> findVacationsByEmployee(EmployeeRepository employeeRepository, VacationRepository vacationRepository,
                                                                       Company company) {
    return employeeRepository.findByCompanyId(company.getId().intValue()).stream()
      .map(employee -> Map.entry(employee, vacationRepository.findByEmployeeId(employee.getId())))
      .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
  }
}

package demo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("employees")
public class Employee {

  @Id
  private Long id;

  private String salary;

  private String firstName;

  private Integer companyId;

  private String email;

  private String lastName;

  public Employee() {
  }

  @Column("id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Column("salary")
  public String getSalary() {
    return salary;
  }

  public void setSalary(String salary) {
    this.salary = salary;
  }

  @Column("first_name")
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  @Column("id_company")
  public Integer getCompanyId() {
    return companyId;
  }

  public void setCompanyId(Integer companyId) {
    this.companyId = companyId;
  }

  @Column("email")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Column("last_name")
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Override
  public String toString() {
    return "Employee{" +
      "id=" + id +
      ", salary='" + salary + '\'' +
      ", firstName='" + firstName + '\'' +
      ", companyId=" + companyId +
      ", email='" + email + '\'' +
      ", lastName='" + lastName + '\'' +
      '}';
  }
}

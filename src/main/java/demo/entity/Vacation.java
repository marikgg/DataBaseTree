package demo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("vacations")
public class Vacation {

  @Id
  private Long id;

  private Long employeeId;

  private String country;

  private Integer days;

  public Vacation() {
  }

  @Column("id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Column("id_employee")
  public Long getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(Long employeeId) {
    this.employeeId = employeeId;
  }

  @Column("country")
  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  @Column("days")
  public Integer getDays() {
    return days;
  }

  public void setDays(Integer days) {
    this.days = days;
  }

  @Override
  public String toString() {
    return "Vacation{" +
      "id=" + id +
      ", employeeId=" + employeeId +
      ", country='" + country + '\'' +
      ", days=" + days +
      '}';
  }
}

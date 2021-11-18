package demo.entity;

import java.math.BigInteger;
import java.math.BigInteger;
import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("company")
public class Company {

  @Id
  private Long id;

  private String name;

  private BigInteger income;

  private LocalDate dataCreated;

  public Company() {
  }

  @Column("id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Column("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Column("income")
  public BigInteger getIncome() {
    return income;
  }

  public void setIncome(BigInteger income) {
    this.income = income;
  }

  @Column("datecreated")
  public LocalDate getDataCreated() {
    return dataCreated;
  }

  public void setDataCreated(LocalDate dataCreated) {
    this.dataCreated = dataCreated;
  }

  @Override
  public String toString() {
    return "Company{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", income='" + income + '\'' +
      ", dataCreated='" + dataCreated + '\'' +
      '}';
  }
}

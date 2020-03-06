package net.slipp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class User {
  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false, length = 20)
  private String userId;
  private String userPassword;
  private String userName;
  private String userEmail;
}

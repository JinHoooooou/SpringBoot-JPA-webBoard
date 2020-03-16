package net.slipp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 20)
  private String userId;
  private String userPassword;
  private String userName;
  private String userEmail;

  public boolean isCorrectId(Long id) {
    if(id == null) {
      return false;
    }

    return id.equals(this.id);
  }

  public boolean isCorrectPassword(String userPassword) {
    return userPassword.equals(this.userPassword);
  }
}

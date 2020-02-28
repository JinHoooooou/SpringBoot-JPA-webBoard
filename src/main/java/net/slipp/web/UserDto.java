package net.slipp.web;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDto {
  private String userId;
  private String userPassword;
  private String userName;
  private String userEmail;
}

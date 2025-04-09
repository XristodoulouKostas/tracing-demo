package com.kchris.aademo.userservice.repositories;

import com.kchris.aademo.userservice.controllers.User;
import com.kchris.aademo.userservice.enums.UserStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "users")
public class UserEntity {

  @Id
  private UUID id;

  private String name;

  @Enumerated(EnumType.STRING)
  private UserStatus status;

  public UserEntity() {
    // Default constructor for JPA
  }

  public UserEntity(UUID id, String name, UserStatus status) {
    this.id = id;
    this.name = name;
    this.status = status;
  }

  public static UserEntity fromDomain(User user) {
    return new UserEntity(user.id(), user.name(), user.status());
  }

  public User toDomain() {
    return new User(this.id, this.name, this.status);
  }

}

package com.kchris.aademo.userservice.repositories;

import com.kchris.aademo.userservice.controllers.User;
import com.kchris.aademo.userservice.enums.UserStatus;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

  private final Map<UUID, User> users;

  public UserRepository() {
    // TODO - Set up data
    UUID id = UUID.randomUUID();
    this.users = Map.of(
        id, new User(id, "Kostas", UserStatus.ACTIVE)
    );
  }

  public Optional<User> findUser(UUID id) {
    return Optional.ofNullable(users.get(id));
  }
}

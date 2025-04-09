package com.kchris.aademo.userservice.services;

import com.kchris.aademo.userservice.domain.User;
import com.kchris.aademo.userservice.repositories.UserEntity;
import com.kchris.aademo.userservice.repositories.UserRepository;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public Optional<User> getUserById(UUID id) {
    return userRepository.findById(id).map(UserEntity::toDomain);
  }

}

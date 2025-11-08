package com.kchris.aademo.userservice.services;

import com.kchris.aademo.userservice.domain.User;
import com.kchris.aademo.userservice.repositories.UserEntity;
import com.kchris.aademo.userservice.repositories.UserRepository;
import commonutilities.ArtificialError;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

  private final UserRepository userRepository;

  private final ArtificialError artificialError = new ArtificialError();

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public Optional<User> getUserById(UUID id) {
    artificialError.randomlyFailWith(0.1, new RuntimeException("Could not send the notification"));
    artificialError.randomlyAddDelay(0.1, 10, ChronoUnit.SECONDS);
    return userRepository.findById(id).map(UserEntity::toDomain);
  }

}

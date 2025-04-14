package com.kchris.aademo.orderservice.clients.users;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class UserService {

  private final RestClient userClient;

  public UserService(@Qualifier("userServiceRestClient") RestClient userClient) {
    this.userClient = userClient;
  }

  public void verifyThatUserIsAllowedToOrder(UUID userId) {
    User user = userClient.get()
        .uri("/users/{id}", userId)
        .retrieve()
        .body(User.class);

    if (user == null || !"ACTIVE".equalsIgnoreCase(user.status())) {
      throw new IllegalStateException("User is not allowed to place orders.");
    }
  }
}

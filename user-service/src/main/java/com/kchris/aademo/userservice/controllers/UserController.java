package com.kchris.aademo.userservice.controllers;

import com.kchris.aademo.userservice.services.UserService;
import java.util.UUID;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping(value = "{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public User getUser(@PathVariable("userId") UUID userId) {
    return userService.getUser(userId);
  }

}

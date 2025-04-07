package com.kchris.aademo.userservice.controllers;

import com.kchris.aademo.userservice.enums.UserStatus;

public record User(
    Object id,
    String name,
    UserStatus status
) {

}

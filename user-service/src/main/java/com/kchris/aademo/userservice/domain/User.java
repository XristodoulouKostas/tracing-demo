package com.kchris.aademo.userservice.domain;

import com.kchris.aademo.userservice.enums.UserStatus;
import java.util.UUID;

public record User(
    UUID id,
    String name,
    UserStatus status
) {

}

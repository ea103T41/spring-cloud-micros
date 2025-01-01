package com.euphy.learn;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserDto {
    String name;
    String email;
}

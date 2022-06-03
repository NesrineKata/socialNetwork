package com.sharing.overload.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class SimpleUserData {

    @Getter @Setter
    private String username;
    @Getter @Setter
    private String password;
}

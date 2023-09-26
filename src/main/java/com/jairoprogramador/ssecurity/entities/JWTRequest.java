package com.jairoprogramador.ssecurity.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class JWTRequest {

    @JsonProperty("user_name")
    private String userName;
    private String password;
}

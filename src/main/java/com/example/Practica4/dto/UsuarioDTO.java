package com.example.Practica4.dto;

import lombok.Data;

@Data
public class UsuarioDTO {
    private String username;
    private String password;
    private String roles;
}
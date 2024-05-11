package org.jan.apiservlet.webapp.headers.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Usuario {
    private Long id;
    private String username;
    private String password;
    private String email;
}
package com.autenticate.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity(name = "auth_user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USERNAME", unique = true)
    @NotNull
    @Size(min = 4)
    private String username;

    @Column(name = "PASSWORD")
    @NotNull
    @Size(min = 4)
    private String password;

    @Column(name = "FIRSTNAME")
    @NotNull
    @Size(min = 4)
    private String firstname;

    @Column(name = "LASTNAME")
    @NotNull
    @Size(min = 4)
    private String lastname;

    @Column(name = "EMAIL")
    @NotNull
    private String email;

    @Column(name = "ENABLED")
    @NotNull
    private Boolean enabled;

    @Column(name = "LAST_PASSWORD_RESET_DATE")
    @NotNull
    private Date lastPasswordResetDate;

    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "USER_ROLE",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")})
    private List<Role> roles;

}

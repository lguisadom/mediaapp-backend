package com.lagm.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "user_data")
public class User {
    @Id
    @EqualsAndHashCode.Include
    private Integer idUser;

    @Column(nullable = false, length = 50, unique = true)
    private String username;

    @Column(nullable = false, length = 60)
    private String password;

    @Column(nullable = false)
    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
        joinColumns = @JoinColumn(name = "id_user", referencedColumnName = "idUser", nullable = false,
                foreignKey = @ForeignKey(name = "FK_USER_ROLE_USER")),
        inverseJoinColumns = @JoinColumn(name = "id_role", referencedColumnName = "idRole", nullable = false,
                foreignKey = @ForeignKey(name = "FK_USER_ROLE_ROLE"))
    )
    private List<Role> roles;
}

package com.lagm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Menu {
    @Id
    @EqualsAndHashCode.Include
    private Integer idMenu;

    @Column(nullable = false, length = 20)
    private String icon;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 30)
    private String url;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "menu_role",
            joinColumns = @JoinColumn(name = "id_menu", referencedColumnName = "idMenu", nullable = false,
                foreignKey = @ForeignKey(name = "FK_MENU_ROLE_MENU")),
            inverseJoinColumns = @JoinColumn(name = "id_role", referencedColumnName = "idRole", nullable = false,
                foreignKey = @ForeignKey(name = "FK_MENU_ROLE_ROLE")
            ))
    private List<Role> roles;
}

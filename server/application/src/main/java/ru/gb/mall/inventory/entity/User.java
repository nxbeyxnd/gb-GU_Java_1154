package ru.gb.mall.inventory.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "USERS")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_idGenerator")
    @SequenceGenerator(name = "seq_idGenerator", sequenceName = "seq_userId", allocationSize = 1)
    @Column(name = "ID")
    private long id;

    @Column(name = "LOGIN", nullable = false, unique = true, columnDefinition = "VARCHAR", length = 20)
    private String login;

    @Column(name = "PASSWORD", nullable = false, columnDefinition = "VARCHAR")
    private String password;

    @Column(name = "FIRSTNAME", nullable = false, columnDefinition = "VARCHAR")
    private String firstName;

    @Column(name = "SECONDNAME", nullable = false, columnDefinition = "VARCHAR")
    private String secondName;

    @ManyToMany
    @JoinTable(
            name = "USER_ROLE_RELATION",
            joinColumns = @JoinColumn(
                    name = "USER_ID",
                    nullable = false,
                    foreignKey = @ForeignKey(
                            name = "FK_USER_ROLE_USER_ID_RELATION",
                            foreignKeyDefinition = "FOREIGN KEY (user_id) REFERENCES users(id) " +
                                    "ON DELETE NO ACTION " +
                                    "ON UPDATE NO ACTION"
                    )
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "ROLE_ID",
                    nullable = false,
                    foreignKey = @ForeignKey(
                            name = "FK_USER_ROLE_ROLE_ID_RELATION",
                            foreignKeyDefinition = "FOREIGN KEY (role_id) REFERENCES roles(id) " +
                                    "ON DELETE NO ACTION " +
                                    "ON UPDATE NO ACTION"
                    )
            )
    )
    private List<Role> roles;
}

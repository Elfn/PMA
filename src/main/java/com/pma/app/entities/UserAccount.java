package com.pma.app.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by Elimane on May, 2020, at 19:53
 */

@Data
@NoArgsConstructor
@Entity
@Table(name = "user_accounts")
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_accounts_seq")
    @SequenceGenerator(name="user_accounts_seq",sequenceName="user_accounts_seq", allocationSize = 1)
    private long user_id;
    private String username;
    private String email;
    private String password;
    private String role = "ROLE_ADMIN";
    private boolean enabled = true;
}

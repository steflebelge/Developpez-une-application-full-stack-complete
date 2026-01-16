package com.openclassrooms.mddapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Table(name = "abonnement")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(AbonnementId.class)
public class AbonnementEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Id
    @ManyToOne
    @JoinColumn(name = "theme_id", nullable = false)
    private ThemeEntity theme;
}

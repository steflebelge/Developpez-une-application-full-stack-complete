package com.openclassrooms.mddapi.entity;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@EqualsAndHashCode
public class AbonnementId implements Serializable {

    private Long user;
    private Long theme;

    public AbonnementId() {
    }

    public AbonnementId(Long user, Long theme) {
        this.user = user;
        this.theme = theme;
    }

    // equals() et hashCode() obligatoires
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbonnementId that)) return false;
        return Objects.equals(user, that.user) &&
                Objects.equals(theme, that.theme);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, theme);
    }
}


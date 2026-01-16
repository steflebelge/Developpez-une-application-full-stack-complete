CREATE TABLE users
(
    id_user  BIGSERIAL PRIMARY KEY,
    email    VARCHAR(50)  NOT NULL UNIQUE,
    name     VARCHAR(20)  NOT NULL,
    password VARCHAR(120) NOT NULL
);

CREATE TABLE theme
(
    id_theme BIGSERIAL PRIMARY KEY,
    name     VARCHAR(20) NOT NULL,
    description     VARCHAR(200) NOT NULL
);

CREATE TABLE abonnement
(
    user_id  BIGINT NOT NULL,
    theme_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, theme_id),
    CONSTRAINT fk_abonnement_user
        FOREIGN KEY (user_id)
            REFERENCES users (id_user)
            ON DELETE CASCADE,
    CONSTRAINT fk_abonnement_theme
        FOREIGN KEY (theme_id)
            REFERENCES theme (id_theme)
            ON DELETE CASCADE
);

CREATE TABLE article
(
    id_article BIGSERIAL PRIMARY KEY,
    theme_id   BIGINT      NOT NULL,
    titre      VARCHAR(30) NOT NULL,
    contenu    TEXT        NOT NULL,
    date       TIMESTAMP   NOT NULL,
    CONSTRAINT fk_article_theme
        FOREIGN KEY (theme_id)
            REFERENCES theme (id_theme)
            ON DELETE CASCADE
);

CREATE TABLE commentaire
(
    id_commentaire BIGSERIAL PRIMARY KEY,
    article_id     BIGINT    NOT NULL,
    message        TEXT      NOT NULL,
    date           TIMESTAMP NOT NULL,
    CONSTRAINT fk_commentaire_article
        FOREIGN KEY (article_id)
            REFERENCES article (id_article)
            ON DELETE CASCADE
);

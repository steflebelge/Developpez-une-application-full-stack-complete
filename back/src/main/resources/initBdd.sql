CREATE TABLE users
(
    id_user  BIGSERIAL PRIMARY KEY,
    email    VARCHAR(50)  NOT NULL UNIQUE,
    name     VARCHAR(20)  NOT NULL,
    password VARCHAR(120) NOT NULL
);

CREATE TABLE theme
(
    id_theme    BIGSERIAL PRIMARY KEY,
    name        VARCHAR(20)  NOT NULL,
    description VARCHAR(200) NOT NULL
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
    id_user    BIGINT      NOT NULL,
    titre      VARCHAR(30) NOT NULL,
    contenu    TEXT        NOT NULL,
    date       TIMESTAMP   NOT NULL,
    CONSTRAINT fk_article_theme
        FOREIGN KEY (theme_id)
            REFERENCES theme (id_theme)
            ON DELETE CASCADE,
    CONSTRAINT fk_article_user
        FOREIGN KEY (id_user)
            REFERENCES users (id_user)
            ON DELETE CASCADE
);

CREATE TABLE commentaire
(
    id_commentaire BIGSERIAL PRIMARY KEY,
    article_id     BIGINT    NOT NULL,
    user_id        BIGINT    NOT NULL,
    message        TEXT      NOT NULL,
    date           TIMESTAMP NOT NULL,

    CONSTRAINT fk_commentaire_article
        FOREIGN KEY (article_id)
            REFERENCES article (id_article)
            ON DELETE CASCADE,

    CONSTRAINT fk_commentaire_user
        FOREIGN KEY (user_id)
            REFERENCES users (id_user)
            ON DELETE CASCADE
);

-- Ajout de données de tests :
-- Mots de passe : "azertyuiop"

INSERT INTO users (email, name, password)
VALUES ('alice@test.com', 'Alice', '$2a$10$A/bK7vLYUTqJiaZCv.UoIecet25IvhFR85YvvBJdvyD1Klnu5Bc7y'),
       ('bob@test.com', 'Bob', '$2a$10$A/bK7vLYUTqJiaZCv.UoIecet25IvhFR85YvvBJdvyD1Klnu5Bc7y');

INSERT INTO theme (name, description)
VALUES ('Java', 'Discussions autour de Java'),
       ('Spring', 'Framework Spring'),
       ('Angular', 'Framework Angular'),
       ('React', 'Framework React'),
       ('DevOps', 'CI/CD et infrastructure'),
       ('Database', 'Bases de données et SQL');


-- Alice : Java, Spring, Database
INSERT INTO abonnement (user_id, theme_id)
VALUES (1, 1),
       (1, 2),
       (1, 6);

-- Bob : Angular, React, DevOps
INSERT INTO abonnement (user_id, theme_id)
VALUES (2, 3),
       (2, 4),
       (2, 5);


INSERT INTO article (theme_id, id_user, titre, contenu, date)
VALUES (1, 1, 'Java 21', 'Présentation des nouveautés de Java 21 Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmodLorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmodLorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod', '2024-01-10 10:00:00'),
       (2, 1, 'Spring Boot', 'Guide Spring Boot pour débutants tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,', '2024-01-11 14:30:00'),
       (3, 2, 'Angular 17', 'Les nouveautés Angular 17 quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodoquis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodoquis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo', '2024-01-12 09:15:00'),
       (4, 2, 'React Hooks', 'Comprendre les hooks React consequat. Duis aute irure dolor in reprehenderit in voluptate velit esseconsequat. Duis aute irure dolor in reprehenderit in voluptate velit esseconsequat. Duis aute irure dolor in reprehenderit in voluptate velit esse', '2024-01-13 16:45:00'),
       (6, 1, 'Index SQL', 'Optimisation avec les index SQL cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat noncillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat noncillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non', '2024-01-14 11:20:00');


INSERT INTO commentaire (article_id, user_id, message, date)
VALUES
-- Article 1
(1, 2, 'Très bon article sur Java.', '2024-01-10 11:00:00'),
(1, 1, 'Merci pour le retour !', '2024-01-10 11:10:00'),

-- Article 2
(2, 2, 'Spring Boot est top.', '2024-01-11 15:00:00'),
(2, 1, 'Oui, surtout pour les APIs.', '2024-01-11 15:05:00'),

-- Article 3
(3, 1, 'Angular évolue vite.', '2024-01-12 10:00:00'),
(3, 2, 'Clairement.', '2024-01-12 10:05:00'),

-- Article 4
(4, 1, 'Hooks très bien expliqués.', '2024-01-13 17:00:00'),
(4, 2, 'Merci !', '2024-01-13 17:05:00'),

-- Article 5
(5, 2, 'Les index changent tout.', '2024-01-14 12:00:00'),
(5, 1, 'Exactement.', '2024-01-14 12:10:00');



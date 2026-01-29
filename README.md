# Projet Fullstack – Front Angular / Back Spring Boot

Ce dépôt contient une application fullstack composée de :
- un frontend Angular (dossier `front`)
- un backend Spring Boot (dossier `back`)

---

## Structure du projet

.
├── front/Application Angular  
└── back/API Spring Boot

---

## Frontend (Angular)

### Prérequis

- Node.js >= 16
- npm >= 8
- Angular CLI 14
- Navigateur web récent

Vérification :
node -v  
npm -v  
ng version

---

### Installation et configuration

Se placer dans le dossier `front` :

cd front

Supprimer les dépendances existantes et installer :

rm -r node_modules/  
npm install

Aucune configuration spécifique n’est requise par défaut.  
L'URL de l’API peut être définit dans `src/app/services/api.service.ts`.

---

### Lancement

npm run start

Application accessible par défaut à l’adresse :
http://localhost:4200

---

## Backend (Spring Boot)

### Prérequis

- Java 17
- Maven 3.9 ou supérieur
- PostgreSQL 13 ou supérieur

Vérification :
java -version  
mvn -version

---

### Installation et configuration

Se placer dans le dossier `back` :

cd back

Configurer la base de données PostgreSQL dans `application.properties` ou `application.yml`.

Exemple :

spring.datasource.url=jdbc:postgresql://localhost:5432/mdd  
spring.datasource.username=postgres  
spring.datasource.password=postgres

Un script d'initialisation de la base de données est disponible dans `src/main/resources/initBdd.sql `

---

### Lancement

Compilation et installation:

mvn clean package -U  
mvn clean install -Dmaven.test.skip=true

Démarrage de l’application :

mvn spring-boot:run

API accessible par défaut à l’adresse :
http://localhost:8080

---

## Technologies utilisées

Frontend :
- Angular 14
- Angular Material
- RxJS

Backend :
- Spring Boot 3
- Spring Security
- Spring Data JPA
- PostgreSQL
- JWT
- MapStruct
- Lombok

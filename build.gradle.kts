plugins {
    java
    id("org.springframework.boot") version "4.1.0"
    id("io.spring.dependency-management") version "1.1.7"
    id("io.freefair.lombok") version "9.5.0"
    id("org.flywaydb.flyway") version "12.8.1"
}

group = "org.example"
version = "0.0.1-SNAPSHOT"
description = "springbatch"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-batch")
    implementation("org.springframework.boot:spring-boot-starter-batch-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-flyway")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.flywaydb:flyway-core:12.8.1")
    implementation("org.flywaydb:flyway-database-postgresql:12.8.1")
    implementation("org.postgresql:postgresql:42.7.11")
    implementation("org.mapstruct:mapstruct:1.6.3")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")
    implementation("com.github.javafaker:javafaker:1.0.2") {
        exclude(group = "org.yaml", module = "snakeyaml")
    }
    implementation("org.yaml:snakeyaml:2.6")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
}

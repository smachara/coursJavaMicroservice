plugins {
    java
    id("org.springframework.boot") version "3.3.0"
    id("io.spring.dependency-management") version "1.1.5"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    developmentOnly("org.springdoc:springdoc-openapi-webmvc-core")
    //http://localhost:8080/swagger-ui.html
    developmentOnly("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.+")
    implementation ("org.springframework.boot:spring-boot-starter-actuator:+")
//Permet à JAVA de se connecter à une base SQL
    runtimeOnly("com.h2database:h2")
//JPA Framework Java qui génère du SQL
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

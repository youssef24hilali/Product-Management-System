plugins {
    java
    id("org.springframework.boot") version "2.5.5"
    id("io.spring.dependency-management") version "1.1.0"
}

group = "com.product"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("io.springfox:springfox-swagger2:2.7.0")
    implementation("io.springfox:springfox-swagger-ui:2.7.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
//    runtimeOnly("com.mysql:mysql-connector-j")
    runtimeOnly("mysql:mysql-connector-java")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

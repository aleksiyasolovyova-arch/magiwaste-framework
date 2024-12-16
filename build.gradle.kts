plugins {
    java
    id("org.springframework.boot") version "3.3.4"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "be.kdg"
version = "0.1.0-TRACERBULLET"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    //postgres stuff
    runtimeOnly("org.postgresql:postgresql")

    //bootstrap
    implementation("org.webjars:webjars-locator-core:0.59")
    implementation("org.webjars:bootstrap:5.3.3")
    implementation("org.webjars.npm:bootstrap-icons:1.11.3")

    //MQTT stuff
    compileOnly("org.springframework.integration:spring-integration-mqtt:6.4.0")
    implementation ("org.springframework:spring-context:6.1.14") //THIS IS HERE BECAUSE OF A 5.3 VULNERABILITY.
//    compileOnly("org.eclipse.paho:org.eclipse.paho.mqtt3.client:1.2.5")



//    runtimeOnly("com.h2database:h2")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

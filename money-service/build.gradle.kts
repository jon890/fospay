import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.2.1"
    id("io.spring.dependency-management") version "1.1.4"

    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")

    id("com.palantir.docker") version "0.35.0"
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
}

noArg {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
}

group = "com.bifos.fospay.banking"
version = "1.0.0"

java {
    sourceCompatibility = JavaVersion.VERSION_17
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
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    implementation("com.mysql:mysql-connector-j:8.2.0")

    val springDocVersion = "2.3.0"
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:${springDocVersion}")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-api:${springDocVersion}")
    implementation("org.springdoc:springdoc-openapi-starter-common:${springDocVersion}")

    developmentOnly("org.springframework.boot:spring-boot-devtools")
//    developmentOnly("org.springframework.boot:spring-boot-docker-compose")

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation(kotlin("stdlib-jdk8"))
    implementation(project(path = ":common"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

docker {
    println(tasks.bootJar.get().outputs.files)
    name = rootProject.name + "-" + project.name + ":" + version
    setDockerfile(file("../Dockerfile"))
    files(tasks.bootJar.get().outputs.files)
    buildArgs(mapOf("JAR_FILE" to tasks.bootJar.get().outputs.files.singleFile.name))
}
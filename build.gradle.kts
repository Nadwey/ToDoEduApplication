plugins {
	java
	id("org.springframework.boot") version "3.2.0"
	id("io.spring.dependency-management") version "1.1.4"
}

group = "com.sap.langer.edu"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.2.0")
	implementation("org.springframework.boot:spring-boot-starter-web:3.2.0")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.modelmapper:modelmapper:2.1.1")
	runtimeOnly("org.hsqldb:hsqldb")

	compileOnly ("org.projectlombok:lombok:1.18.30")
	annotationProcessor("org.projectlombok:lombok:1.18.30")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

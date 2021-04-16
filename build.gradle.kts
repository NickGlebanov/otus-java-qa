import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.4.4"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.4.31"
	kotlin("plugin.spring") version "1.4.31"
}

group = "ru.otus.glebanov"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
	//spring dependencies
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	//other dependencies
	// https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java
	implementation("org.seleniumhq.selenium:selenium-java:3.141.59")
	// https://mvnrepository.com/artifact/io.github.bonigarcia/webdrivermanager
	implementation("io.github.bonigarcia:webdrivermanager:4.4.0")
	// https://mvnrepository.com/artifact/org.assertj/assertj-core
	implementation("org.assertj:assertj-core:3.19.0")

}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

package ru.otus.glebanov.javaqa

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(FrameworkProperties::class)
class JavaQaApplication

fun main(args: Array<String>) {
	runApplication<JavaQaApplication>(*args)
}

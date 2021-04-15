package ru.otus.glebanov.javaqa

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class JavaQaApplication

fun main(args: Array<String>) {
	runApplication<JavaQaApplication>(*args)
}

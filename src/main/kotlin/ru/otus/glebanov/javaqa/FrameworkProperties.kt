package ru.otus.glebanov.javaqa

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties
class FrameworkProperties {

    lateinit var browser: String
    var waitForElementInSeconds: Long = 0
}
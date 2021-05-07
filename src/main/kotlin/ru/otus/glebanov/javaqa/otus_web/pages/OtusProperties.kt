package ru.otus.glebanov.javaqa.otus_web.pages

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties
class OtusProperties {

    lateinit var mainPageUrl: String
}
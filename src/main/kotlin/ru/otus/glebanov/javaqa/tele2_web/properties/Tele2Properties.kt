package ru.otus.glebanov.javaqa.tele2_web.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties
class Tele2Properties {

    lateinit var numberShopUrl: String
}
package ru.otus.glebanov.javaqa.yandex_market_web.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties
class YandexMarketProperties {

    lateinit var mainPageUrl: String

    lateinit var compareUrl: String
}
package ru.otus.glebanov.javaqa.yandex_market_web.page_factory

import org.openqa.selenium.WebDriver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.otus.glebanov.javaqa.yandex_market_web.pages.MainMarketPage
import ru.otus.glebanov.javaqa.yandex_market_web.properties.YandexMarketProperties

@Component
class YandexMarketPageFactory(
        @Autowired private val yandexMarketProperties: YandexMarketProperties
) {

    fun getMainPage(driver: WebDriver): MainMarketPage = MainMarketPage(driver, yandexMarketProperties)

}
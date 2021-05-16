package ru.otus.glebanov.javaqa.yandex_market_web.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.slf4j.LoggerFactory
import ru.otus.glebanov.javaqa.yandex_market_web.properties.YandexMarketProperties

class MainMarketPage(
        private val driver: WebDriver,
        private val yandexMarketProperties: YandexMarketProperties
) {
    private val logger = LoggerFactory.getLogger(MainMarketPage::class.java)

    fun open() {
        driver.get(yandexMarketProperties.mainPageUrl)
        logger.info("Открыт url: ${yandexMarketProperties.mainPageUrl}")
    }

    fun elementSearchNumberInput() = driver.findElement(By.xpath("//input[@id='searchNumber']"))

}
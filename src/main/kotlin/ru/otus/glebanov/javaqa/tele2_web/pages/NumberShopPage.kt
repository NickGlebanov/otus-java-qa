package ru.otus.glebanov.javaqa.tele2_web.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.slf4j.LoggerFactory
import ru.otus.glebanov.javaqa.tele2_web.properties.Tele2Properties

class NumberShopPage(
        private val driver: WebDriver,
        private val tele2Properties: Tele2Properties
) {
    private val logger = LoggerFactory.getLogger(NumberShopPage::class.java)

    fun open() {
        driver.get(tele2Properties.numberShopUrl)
        logger.info("Открыт url: ${tele2Properties.numberShopUrl}")
    }

    fun elementSearchNumberInput() = driver.findElement(By.xpath("//input[@id='searchNumber']"))

}
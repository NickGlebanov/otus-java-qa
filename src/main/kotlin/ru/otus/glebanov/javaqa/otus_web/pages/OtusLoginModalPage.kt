package ru.otus.glebanov.javaqa.otus_web.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.slf4j.LoggerFactory
import ru.otus.glebanov.javaqa.otus_web.properties.OtusProperties

class OtusLoginModalPage(
        private val driver: WebDriver,
        private val otusProperties: OtusProperties
) {
    private val logger = LoggerFactory.getLogger(OtusLoginModalPage::class.java)

    private val emailModalXpath = "//form[@class='new-log-reg__form js-login']//input[@required and contains(@class,'js-email')]"
    private val passwordModalXpath = "//form[@class='new-log-reg__form js-login']//input[@required and contains(@name,'password')]"
    private val buttonLoginModalXpath = "//form[@class='new-log-reg__form js-login']//button"

    fun login() {
        driver.findElement(By.xpath(emailModalXpath)).sendKeys(otusProperties.email)
        driver.findElement(By.xpath(passwordModalXpath)).sendKeys(otusProperties.password)
        driver.findElement(By.xpath(buttonLoginModalXpath)).click()
        logger.info("Залогинились с почтой ${otusProperties.email}")
    }
}
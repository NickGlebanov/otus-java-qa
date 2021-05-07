package ru.otus.glebanov.javaqa.otus_web.pages

import org.openqa.selenium.WebDriver
import org.slf4j.LoggerFactory

class OtusMainPage(
        private val driver: WebDriver,
        private val otusProperties: OtusProperties
) {
    private val logger = LoggerFactory.getLogger(OtusMainPage::class.java)

    fun get() {
        driver.get(otusProperties.mainPageUrl)
        logger.info("Открыт url: ${otusProperties.mainPageUrl}")
    }
}
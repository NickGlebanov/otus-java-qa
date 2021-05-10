package ru.otus.glebanov.javaqa.otus_web.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.slf4j.LoggerFactory

class OtusMainPage(
        private val driver: WebDriver,
        private val otusProperties: OtusProperties
) {
    private val logger = LoggerFactory.getLogger(OtusMainPage::class.java)

    private val contactsHeaderLinkXpath = "//a[@class='header2_subheader-link' and @title='Контакты']"

    fun get() {
        driver.get(otusProperties.mainPageUrl)
        logger.info("Открыт url: ${otusProperties.mainPageUrl}")
    }

    fun goToContacts() {
        driver.findElement(By.xpath(contactsHeaderLinkXpath)).click()
        logger.info("Перешли в Контакты")
    }

}
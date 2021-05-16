package ru.otus.glebanov.javaqa.otus_web.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.slf4j.LoggerFactory
import ru.otus.glebanov.javaqa.otus_web.properties.OtusProperties

class OtusMainPage(
        private val driver: WebDriver,
        private val otusProperties: OtusProperties
) {
    private val logger = LoggerFactory.getLogger(OtusMainPage::class.java)

    private val contactsHeaderLinkXpath = "//a[@class='header2_subheader-link' and @title='Контакты']"
    private val faqHeaderLinkXpath = "//a[@class='header2_subheader-link' and @title='FAQ']"


    fun get() {
        driver.get(otusProperties.mainPageUrl)
        logger.info("Открыт url: ${otusProperties.mainPageUrl}")
    }

    fun goToContacts() {
        driver.findElement(By.xpath(contactsHeaderLinkXpath)).click()
        logger.info("Перешли в Контакты")
    }

    fun goToFAQ() {
        driver.findElement(By.xpath(faqHeaderLinkXpath)).click()
        logger.info("Перешли в FAQ")
    }

    fun subscribe(email: String) {
        driver.findElement(By.xpath("//input[@class='input footer2__subscribe-input']"))
                .sendKeys(email)
        driver.findElement(
                By.xpath("//button[@class='footer2__subscribe-button button button_blue button_as-input']")
        ).click()
    }

    fun elementSubscribeModalSuccess() = driver.findElement(
            By.xpath("//p[@class='subscribe-modal__success']")
    )

}
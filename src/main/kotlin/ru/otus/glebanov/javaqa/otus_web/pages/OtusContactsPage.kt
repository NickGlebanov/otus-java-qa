package ru.otus.glebanov.javaqa.otus_web.pages

import org.assertj.core.api.Assertions.assertThat
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.slf4j.LoggerFactory

class OtusContactsPage(
        private val driver: WebDriver,
        private val otusProperties: OtusProperties
) {
    private val logger = LoggerFactory.getLogger(OtusContactsPage::class.java)

    private val addressXpath = "//div[text()='Адрес']/../div[contains(@class,'styles__Content')]"

    fun checkAddressEqualsTo(text: String) {
        assertThat(driver.findElement(By.xpath(addressXpath)).text).isEqualTo(text)
    }
}
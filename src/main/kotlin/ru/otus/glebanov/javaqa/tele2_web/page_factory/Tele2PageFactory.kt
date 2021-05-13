package ru.otus.glebanov.javaqa.tele2_web.page_factory

import org.openqa.selenium.WebDriver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.otus.glebanov.javaqa.tele2_web.pages.NumberShopPage
import ru.otus.glebanov.javaqa.tele2_web.properties.Tele2Properties

@Component
class Tele2PageFactory(
        @Autowired private val tele2Properties: Tele2Properties
) {

    fun getNumberShopPage(driver: WebDriver): NumberShopPage = NumberShopPage(driver, tele2Properties)

}
package ru.otus.glebanov.javaqa.otus_web.page_factory

import org.openqa.selenium.WebDriver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.otus.glebanov.javaqa.otus_web.pages.*
import ru.otus.glebanov.javaqa.otus_web.properties.OtusProperties

@Component
class OtusPageFactory(
        @Autowired private val otusProperties: OtusProperties
) {

    fun getMainPage(driver: WebDriver): OtusMainPage = OtusMainPage(driver, otusProperties)

    fun getContactsPage(driver: WebDriver): OtusContactsPage = OtusContactsPage(driver, otusProperties)

    fun getFAQPage(driver: WebDriver): OtusFAQPage = OtusFAQPage(driver, otusProperties)

    fun getLoginModalPage(driver: WebDriver): OtusLoginModalPage = OtusLoginModalPage(driver,otusProperties)

    fun getProfilePage(driver: WebDriver): OtusProfilePage = OtusProfilePage(driver, otusProperties)

}
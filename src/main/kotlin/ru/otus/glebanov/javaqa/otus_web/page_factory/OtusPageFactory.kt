package ru.otus.glebanov.javaqa.otus_web.page_factory

import org.openqa.selenium.WebDriver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.otus.glebanov.javaqa.otus_web.pages.OtusContactsPage
import ru.otus.glebanov.javaqa.otus_web.pages.OtusFAQPage
import ru.otus.glebanov.javaqa.otus_web.pages.OtusMainPage
import ru.otus.glebanov.javaqa.otus_web.properties.OtusProperties

@Component
class OtusPageFactory(
        @Autowired private val otusProperties: OtusProperties
) {

    fun getMainPage(driver: WebDriver): OtusMainPage = OtusMainPage(driver, otusProperties)

    fun getContactsPage(driver: WebDriver): OtusContactsPage = OtusContactsPage(driver, otusProperties)

    fun getFAQPage(driver: WebDriver): OtusFAQPage = OtusFAQPage(driver, otusProperties)

}
package ru.otus.glebanov.javaqa.otus_web.pages

import org.openqa.selenium.WebDriver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.otus.glebanov.javaqa.FrameworkProperties

@Component
class PageFactory(
        @Autowired val otusProperties: OtusProperties
) {

    fun getMainPage(driver: WebDriver): OtusMainPage = OtusMainPage(driver, otusProperties)

    fun getContactsPage(driver: WebDriver): OtusContactsPage = OtusContactsPage(driver, otusProperties)

}
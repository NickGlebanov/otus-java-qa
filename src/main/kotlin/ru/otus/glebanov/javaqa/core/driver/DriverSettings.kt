package ru.otus.glebanov.javaqa.core.driver

import org.openqa.selenium.WebDriver
import ru.otus.glebanov.javaqa.FrameworkProperties

class DriverSettings(
        private val driver: WebDriver,
        private val frameworkProperties: FrameworkProperties
) {

    fun maximizeWindow() = driver.manage().window().maximize()

    fun getDriver(): WebDriver = driver

}
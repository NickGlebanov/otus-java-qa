package ru.otus.glebanov.javaqa.core.helpers

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

class JSHelper(private val driver: WebDriver) {

    fun scrollIntoView(elem: WebElement) = (driver as JavascriptExecutor)
            .executeScript("arguments[0].scrollIntoView()", elem)
}
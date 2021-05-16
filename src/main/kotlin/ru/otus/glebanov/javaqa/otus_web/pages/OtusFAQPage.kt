package ru.otus.glebanov.javaqa.otus_web.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.slf4j.LoggerFactory
import ru.otus.glebanov.javaqa.otus_web.properties.OtusProperties

class OtusFAQPage(
        private val driver: WebDriver,
        private val otusProperties: OtusProperties
) {
    private val logger = LoggerFactory.getLogger(OtusFAQPage::class.java)

    private val questionWhereCourseProgramXpath = "//div[text()='Где посмотреть программу интересующего курса?']"
    private val answerWhereCourseProgramXpath = "$questionWhereCourseProgramXpath/../div[contains(@class,'answer')]"

    fun questionWhereCourseProgramElement(): WebElement = driver.findElement(By.xpath(questionWhereCourseProgramXpath))
    fun answerWhereCourseProgramElement(): WebElement = driver.findElement(By.xpath(answerWhereCourseProgramXpath))

}
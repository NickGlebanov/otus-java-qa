package ru.otus.glebanov.javaqa.core.driver

import org.openqa.selenium.Dimension
import org.openqa.selenium.WebDriver
import org.slf4j.LoggerFactory
import ru.otus.glebanov.javaqa.FrameworkProperties
import java.util.concurrent.TimeUnit

class DriverSettings(
        private val driver: WebDriver,
        private val frameworkProperties: FrameworkProperties
) {
    private val logger = LoggerFactory.getLogger(DriverSettings::class.java)

    fun maximizeWindow() = driver.manage().window().maximize()

    fun configureImplicitWaits() {
        driver.manage().timeouts().implicitlyWait(frameworkProperties.waitForElementInSeconds, TimeUnit.SECONDS)
        logger.info("Настроено неявное ожидание в ${frameworkProperties.waitForElementInSeconds} секунд")
    }

    fun setWindowSize(width: Int, height: Int) {
        driver.manage().window().size = Dimension(width, height)
        logger.info("Размер браузера установлен - ширина: $width, высота: $height")
    }

    fun getDriver(): WebDriver = driver

}
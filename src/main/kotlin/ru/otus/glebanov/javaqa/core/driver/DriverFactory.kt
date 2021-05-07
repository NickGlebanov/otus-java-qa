package ru.otus.glebanov.javaqa.core.driver

import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.edge.EdgeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.otus.glebanov.javaqa.FrameworkProperties

@Component
class DriverFactory(
        @Autowired val frameworkProperties: FrameworkProperties
) {

    val logger = LoggerFactory.getLogger(DriverFactory::class.java)

    fun getDriverSettings(): DriverSettings {
        var driver: WebDriver? = null
        when (frameworkProperties.browser) {
            "CHROME" -> {
                WebDriverManager.chromedriver().setup()
                driver = ChromeDriver()
            }
            "FIREFOX" -> {
                WebDriverManager.firefoxdriver().setup()
                driver = FirefoxDriver()
            }
            "EDGE" -> {
                WebDriverManager.edgedriver().setup()
                driver = EdgeDriver()
            }
        }
        logger.info("Драйвер для браузера ${frameworkProperties.browser} установлен")
        return DriverSettings(driver!!, frameworkProperties)
    }
}
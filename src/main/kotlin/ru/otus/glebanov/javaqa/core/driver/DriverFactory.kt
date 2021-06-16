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
    enum class Browsers {
        CHROME,FIREFOX,EDGE
    }
    val logger = LoggerFactory.getLogger(DriverFactory::class.java)

    fun getDriverSettings(): DriverSettings {
        val driver = when (Browsers.valueOf(frameworkProperties.browser)) {
            Browsers.CHROME -> {
                WebDriverManager.chromedriver().setup()
                ChromeDriver()
            }
            Browsers.FIREFOX -> {
                WebDriverManager.firefoxdriver().setup()
                FirefoxDriver()
            }
            Browsers.EDGE -> {
                WebDriverManager.edgedriver().setup()
                EdgeDriver()
            }
        }
        logger.info(frameworkProperties.browser)
        return DriverSettings(driver!!, frameworkProperties)
    }
}
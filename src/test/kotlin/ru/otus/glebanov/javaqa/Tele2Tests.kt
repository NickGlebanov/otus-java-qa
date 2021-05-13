package ru.otus.glebanov.javaqa

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.WebDriverWait
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import ru.otus.glebanov.javaqa.core.driver.DriverFactory
import ru.otus.glebanov.javaqa.core.driver.DriverSettings
import ru.otus.glebanov.javaqa.tele2_web.page_factory.Tele2PageFactory

@SpringBootTest
@TestPropertySource(locations = ["classpath:framework.yml", "classpath:tele2.yml"])
class Tele2Tests(
        @Autowired val driverFactory: DriverFactory,
        @Autowired val tele2PageFactory: Tele2PageFactory
) {

    lateinit var driver: WebDriver
    lateinit var driverSettings: DriverSettings
    private val logger = LoggerFactory.getLogger(Tele2Tests::class.java)
    private val wait by lazy { WebDriverWait(driver, 10) }

    @BeforeEach
    fun setUp() {
        logger.info("Настройка окружения")
        driverSettings = driverFactory.getDriverSettings()
        driverSettings.configureImplicitWaits()
        driver = driverSettings.getDriver()
    }

    @Test
    fun waitForNumbersTest() {
        val numberShopPage = tele2PageFactory.getNumberShopPage(driver)
        numberShopPage.open()
        numberShopPage.elementSearchNumberInput().sendKeys("97")
        val loaderXpath = "//span[@class='catalog-overlay loader-overlay']"
        wait.until {
            driver.findElements(By.xpath(loaderXpath)).count() == 1
        }
        wait.until {
            driver.findElements(By.xpath(loaderXpath)).count() == 0
        }
        wait.until {
            driver.findElements(
                    By.xpath("//span[@class='phone-number']" +
                            "//span[@class='area-code' and contains(text(),'9')]"))
                    .count() > 1
        }
        assertThat(
                driver.findElements(By.xpath("//span[@class='phone-number']"))
                .stream().map { it.text }.filter {
                    it.contains("97") || it.contains("9-7")
                }
                .count()
        ).isEqualTo(24)
    }

    @AfterEach
    fun setDown() {
        logger.info("Закрываем драйвер")
        driver.quit()
    }

}
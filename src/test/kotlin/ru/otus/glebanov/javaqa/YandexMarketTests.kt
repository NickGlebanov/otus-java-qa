package ru.otus.glebanov.javaqa

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.openqa.selenium.WebDriver
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import ru.otus.glebanov.javaqa.core.driver.DriverFactory
import ru.otus.glebanov.javaqa.core.driver.DriverSettings
import ru.otus.glebanov.javaqa.yandex_market_web.page_factory.YandexMarketPageFactory

@SpringBootTest
@TestPropertySource(locations = ["classpath:framework.yml", "classpath:yandex_market.yml"])
class YandexMarketTests(
        @Autowired val driverFactory: DriverFactory,
        @Autowired val yandexMarketPageFactory: YandexMarketPageFactory
) {

    lateinit var driver: WebDriver
    lateinit var driverSettings: DriverSettings
    private val logger = LoggerFactory.getLogger(Tele2Tests::class.java)

    @BeforeEach
    fun setUp() {
        logger.info("Настройка окружения")
        driverSettings = driverFactory.getDriverSettings()
        driverSettings.configureImplicitWaits()
        driver = driverSettings.getDriver()
        driverSettings.setWindowSize(1050, 660)
    }

    @AfterEach
    fun setDown() {
        logger.info("Закрываем драйвер")
        driver.quit()
    }
}
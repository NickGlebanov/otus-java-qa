package ru.otus.glebanov.javaqa

import org.awaitility.Awaitility.await
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.openqa.selenium.WebDriver
import org.openqa.selenium.interactions.Actions
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import ru.otus.glebanov.javaqa.core.driver.DriverFactory
import ru.otus.glebanov.javaqa.core.driver.DriverSettings
import ru.otus.glebanov.javaqa.core.helpers.JSHelper
import ru.otus.glebanov.javaqa.yandex_market_web.page_factory.YandexMarketPageFactory
import java.util.concurrent.TimeUnit


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

    @Test
    fun compareTwoProducts() {
        val mainMarketPage = yandexMarketPageFactory.getMainPage(driver)
        mainMarketPage.open()
        mainMarketPage.catalogButton().click()
        Actions(driver).moveToElement(mainMarketPage.catalogTitleByName("Электроника"))
                .build().perform()
        mainMarketPage.catalogLinkByName("Смартфоны").click()
        mainMarketPage.activateManufacturerSearch()
        mainMarketPage.addManufacturerFilter("Samsung")
        mainMarketPage.addManufacturerFilter("Xiaomi")
        mainMarketPage.sortButtonByType("по цене").click()
        Thread.sleep(3000)
        val item1 = mainMarketPage.addToCompare("Samsung", 0)
        val item2 = mainMarketPage.addToCompare("Xiaomi", 0)
        mainMarketPage.toCompare()
        mainMarketPage.checkItemsInComparingList(mutableListOf(item1, item2))
    }

    @AfterEach
    fun setDown() {
        logger.info("Закрываем драйвер")
        driver.quit()
    }
}
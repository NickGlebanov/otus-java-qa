package ru.otus.glebanov.javaqa

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.*
import org.openqa.selenium.WebDriver
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import ru.otus.glebanov.javaqa.core.driver.DriverFactory
import ru.otus.glebanov.javaqa.core.driver.DriverSettings
import ru.otus.glebanov.javaqa.otus_web.pages.PageFactory

@SpringBootTest
@EnableConfigurationProperties(value = [FrameworkProperties::class])
@TestPropertySource(locations = ["classpath:framework.yml", "classpath:otus.yml"] )
class OtusTests(
        @Autowired val driverFactory: DriverFactory,
        @Autowired val pageFactory: PageFactory
) {

    lateinit var driver: WebDriver
    lateinit var driverSettings: DriverSettings
    private val logger = LoggerFactory.getLogger(OtusTests::class.java)

    @BeforeEach
    fun setUp() {
        logger.info("Настройка окружения")
        driverSettings = driverFactory.getDriverSettings()
        driverSettings.configureImplicitWaits()
        driver = driverSettings.getDriver()
    }

    @Test
    fun contextLoads() {
        logger.info("Тест начат")
        val otusMainPage = pageFactory.getMainPage(driver)
        otusMainPage.get()
        assertThat(driver.title).contains("Онлайн‑курсы")
    }

    @Test
    fun checkAddress() {
        val otusMainPage = pageFactory.getMainPage(driver)
        otusMainPage.get()
        otusMainPage.goToContacts()
        val otusContactsPage = pageFactory.getContactsPage(driver)
        otusContactsPage.checkAddressEqualsTo("125167, г. Москва, Нарышкинская аллея., д. 5, стр. 2, тел. +7 499 938-92-02")
        driverSettings.maximizeWindow()
        assertThat(driver.title).contains("Контакты")

    }

    @AfterEach
    fun setDown() {
        logger.info("Закрываем драйвер")
        driver?.quit()
    }

}

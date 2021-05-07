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
import ru.otus.glebanov.javaqa.otus_web.pages.OtusMainPage
import ru.otus.glebanov.javaqa.otus_web.pages.PageFactory

@SpringBootTest
@EnableConfigurationProperties(value = [FrameworkProperties::class])
@TestPropertySource(locations = ["classpath:framework.yml", "classpath:otus.yml"] )
class JavaQaApplicationTests(
        @Autowired val driverFactory: DriverFactory,
        @Autowired val pageFactory: PageFactory
) {

    lateinit var driver: WebDriver
    lateinit var driverSettings: DriverSettings
    private val logger = LoggerFactory.getLogger(JavaQaApplicationTests::class.java)

    @BeforeEach
    fun setUp() {
        logger.info("Настройка окружения")
        driverSettings = driverFactory.getDriverSettings()
        driver = driverSettings.getDriver()
    }

    @Test
    fun contextLoads() {
        logger.info("Тест начат")
        val otusMainPage = pageFactory.getMainPage(driver)
        otusMainPage.get()
        assertThat(driver.title).contains("Онлайн‑курсы")
    }

    @AfterEach
    fun setDown() {
        logger.info("Закрываем драйвер")
        driver?.quit()
    }

}

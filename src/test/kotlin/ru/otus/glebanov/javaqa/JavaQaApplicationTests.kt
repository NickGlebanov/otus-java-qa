package ru.otus.glebanov.javaqa

import org.junit.jupiter.api.*
import org.openqa.selenium.WebDriver
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.event.annotation.AfterTestClass
import org.springframework.test.context.event.annotation.AfterTestMethod
import org.springframework.test.context.event.annotation.BeforeTestClass
import org.springframework.test.context.event.annotation.BeforeTestMethod
import ru.otus.glebanov.javaqa.core.DriverFactory

@SpringBootTest
@EnableConfigurationProperties(value = [FrameworkProperties::class])
@TestPropertySource("classpath:framework.yml")
class JavaQaApplicationTests(
        @Autowired val driverFactory: DriverFactory,
        @Autowired val frameworkProperties: FrameworkProperties
) {

    lateinit var driver: WebDriver
    private val logger = LoggerFactory.getLogger(JavaQaApplicationTests::class.java)

    @BeforeEach
    fun setUp() {
        logger.info("Настройка окружения")
        driver = driverFactory.getDriver()
    }

    @Test
    fun contextLoads() {
        logger.info("Тест начат")
        driver.get(frameworkProperties.url)
        logger.info("Открыт url: ${frameworkProperties.url}")
    }

    @AfterEach
    fun setDown() {
        logger.info("Закрываем драйвер")
        driver?.quit()
    }

}

package ru.otus.glebanov.javaqa

import org.apache.commons.lang3.RandomStringUtils
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
import ru.otus.glebanov.javaqa.core.helpers.JSHelper
import ru.otus.glebanov.javaqa.otus_web.page_factory.OtusPageFactory
import java.lang.Thread.sleep

@SpringBootTest
@TestPropertySource(locations = ["classpath:framework.yml", "classpath:otus.yml"])
class OtusTests(
        @Autowired val driverFactory: DriverFactory,
        @Autowired val otusPageFactory: OtusPageFactory
) {

    lateinit var driver: WebDriver
    lateinit var driverSettings: DriverSettings
    private val logger = LoggerFactory.getLogger(OtusTests::class.java)

    private val whereCourseProgrammAnswerText = "Программу курса в сжатом виде можно увидеть на странице курса после " +
            "блока с преподавателями. Подробную программу курса можно скачать кликнув на " +
            "“Скачать подробную программу курса”"

    @BeforeEach
    fun setUp() {
        logger.info("Настройка окружения")
        driverSettings = driverFactory.getDriverSettings()
        driverSettings.configureImplicitWaits()
        driver = driverSettings.getDriver()
    }

    @Test
    fun pageTitleTest() {
        logger.info("Тест начат")
        val otusMainPage = otusPageFactory.getMainPage(driver)
        otusMainPage.get()
        assertThat(driver.title).contains("Онлайн‑курсы")
    }

    @Test
    fun addressTest() {
        val otusMainPage = otusPageFactory.getMainPage(driver)
        otusMainPage.get()
        otusMainPage.goToContacts()
        val otusContactsPage = otusPageFactory.getContactsPage(driver)
        otusContactsPage.checkAddressEqualsTo("125167, г. Москва, Нарышкинская аллея., д. 5, стр. 2, тел. +7 499 938-92-02")
        driverSettings.maximizeWindow()
        assertThat(driver.title).contains("Контакты")
    }

    @Test
    fun courseProgramTest() {
        driverSettings.setWindowSize(1050, 660)
        val otusMainPage = otusPageFactory.getMainPage(driver)
        otusMainPage.get()
        otusMainPage.goToFAQ()
        val otusFAQPage = otusPageFactory.getFAQPage(driver)
        otusFAQPage.questionWhereCourseProgramElement().click()
        JSHelper(driver).scrollIntoView(otusFAQPage.answerWhereCourseProgramElement())
        assertThat(otusFAQPage.answerWhereCourseProgramElement().text)
                .isEqualTo(whereCourseProgrammAnswerText)
    }

    @Test
    fun subscribeTest() {
        val otusMainPage = otusPageFactory.getMainPage(driver)
        otusMainPage.get()
        otusMainPage
                .subscribe("${RandomStringUtils.randomAlphanumeric(9)}@rambler.com")
        assertThat(otusMainPage.elementSubscribeModalSuccess().text).isEqualTo("Вы успешно подписались")
    }

    @Test
    fun profileAddContactsTest() {
        var otusMainPage = otusPageFactory.getMainPage(driver)
        otusMainPage.get()
        otusMainPage.goToLoginModal()
        val otusLoginModalPage = otusPageFactory.getLoginModalPage(driver)
        otusLoginModalPage.login()
        otusMainPage.goToProfile()
        val otusProfilePage = otusPageFactory.getProfilePage(driver)
        otusProfilePage.deleteOldContacts()
        otusProfilePage.addAddNewContactFieldWithValue("one")
        otusProfilePage.addAddNewContactFieldWithValue("two")
        otusProfilePage.save()
        driver.manage().deleteAllCookies()
        otusMainPage.get()
        otusMainPage.goToLoginModal()
        otusLoginModalPage.login()
        otusMainPage.goToProfile()
        otusProfilePage.checkContactWithValue("one")
        otusProfilePage.checkContactWithValue("two")
    }

    @AfterEach
    fun setDown() {
        logger.info("Закрываем драйвер")
        driver.quit()
    }

}

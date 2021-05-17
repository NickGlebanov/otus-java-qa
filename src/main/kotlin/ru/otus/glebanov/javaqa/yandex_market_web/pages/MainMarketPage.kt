package ru.otus.glebanov.javaqa.yandex_market_web.pages

import org.assertj.core.api.Assertions.assertThat
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.slf4j.LoggerFactory
import ru.otus.glebanov.javaqa.yandex_market_web.properties.YandexMarketProperties

class MainMarketPage(
        private val driver: WebDriver,
        private val yandexMarketProperties: YandexMarketProperties
) {
    private val logger = LoggerFactory.getLogger(MainMarketPage::class.java)

    fun getFirstItemName(): String {
        return driver.findElements(
                By.xpath("//article[@data-autotest-id]//h3//a")
        )[0].getAttribute("title")
    }

    fun open() {
        driver.get(yandexMarketProperties.mainPageUrl)
        logger.info("Открыт url: ${yandexMarketProperties.mainPageUrl}")
    }

    fun catalogButton(): WebElement = driver
            .findElement(By.xpath("//div[@data-apiary-widget-id='/header/catalogEntrypoint']"))

    fun catalogLinkByName(linkName: String): WebElement = driver
            .findElement(By.xpath("//a[contains(@href,'catalog') and text()='$linkName']"))

    fun catalogTitleByName(titleName: String): WebElement = driver
            .findElement(By.xpath("//li//a[contains(@href,'catalog')]//span[text()='$titleName']"))

    fun activateManufacturerSearch() {
        driver.findElement(
                By.xpath("//legend[text()='Производитель']/..//button[text()='Показать всё']")
        ).click()
    }

    fun addManufacturerFilter(manufacturerName: String) {
        val manufacturerInput = driver.findElement(
                By.xpath(
                        "//legend[text()='Производитель']/..//input[@name='Поле поиска']"
                )
        )
        manufacturerInput.clear()
        manufacturerInput.sendKeys(manufacturerName)
        driver.findElement(By.xpath("//input[@name='Производитель $manufacturerName']/.."))
                .click()
        logger.info("Добавлен фильтр $manufacturerName")
    }

    fun sortButtonByType(type: String): WebElement =
            driver.findElement(By.xpath("//button[text()='$type']"))

    fun addToCompare(brandName: String, numberOfItem: Int): String {
        val itemTitle = driver.findElements(
                By.xpath("//article[@data-autotest-id]//a[contains(@title,'$brandName')]")
        )[numberOfItem].getAttribute("title")

        Actions(driver).moveToElement(driver.findElements(
                By.xpath("//article[@data-autotest-id]//a[contains(@title,'$brandName')]")
        )[numberOfItem]).build().perform()
        driver.findElement(
                By.xpath("//article[@data-autotest-id]//a[contains(@title,'$itemTitle')]/.." +
                        "/../../..//div[contains(@aria-label,'сравнению')]")
        ).click()

        assertThat(
                driver.findElement(
                        By.xpath("//div[@data-apiary-widget-id='/content/popupInformer']//div[contains(text(),'Товар')]")
                ).text
        ).isEqualTo("Товар $itemTitle добавлен к сравнению")
        return itemTitle
    }

    fun toCart() = driver.findElement(By.xpath("//a[contains(@href,'cart')]")).click()

    fun assertThatCartContainNitems(numberOfItem: Int) {
        assertThat(driver.findElement(By.xpath("//h1[contains(text(),'в корзине')]")).text)
                .contains(numberOfItem.toString())
    }

    fun toCompare() = driver.findElement(By.xpath("//span[text()='Сравнить']")).click()

    fun checkItemsInComparingList(items: MutableCollection<String>) {
        items.forEach {
            driver.findElement(
                    By.xpath("//a[text()='$it']")
            )
        }
    }
}
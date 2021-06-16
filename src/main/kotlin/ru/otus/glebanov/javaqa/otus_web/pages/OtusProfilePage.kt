package ru.otus.glebanov.javaqa.otus_web.pages

import org.assertj.core.api.Assertions.assertThat
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.slf4j.LoggerFactory
import ru.otus.glebanov.javaqa.otus_web.properties.OtusProperties


class OtusProfilePage(
    private val driver: WebDriver,
    private val otusProperties: OtusProperties
) {
    private val logger = LoggerFactory.getLogger(OtusProfilePage::class.java)

    private val buttonAddContactXpath = "//div[@data-prefix='contact']//button[contains(@class,'add')]"
    private val buttonDeleteContactXpath = "//div[@data-prefix='contact']//button[contains(@class,'delete')]"
    private val buttonSaveAndContinueXpath = "//button[@title='Сохранить и продолжить']"

    private val inputsNewContactXpath = "//input[contains(@id,'value')]"
    private val contactsContainersXpath = "//div[contains(@class,'container') and @data-num]"

    class DataNumWebElementComparator : Comparator<WebElement> {

        private fun getDataIdFromElement(elm: WebElement): Int {
            return elm.getAttribute("data-num").toInt()
        }

        override fun compare(p0: WebElement, p1: WebElement): Int {
            val p0data = getDataIdFromElement(p0)
            val p1data = getDataIdFromElement(p1)
            return if (p0data > p1data ) 1 else if (p0data < p1data) -1 else 0
        }

    }


    fun addAddNewContactFieldWithValue(value: String) {
        driver.findElement(By.xpath(buttonAddContactXpath)).click()
        val elms = driver.findElements(By.xpath(contactsContainersXpath))
        val lastElement = elms.stream().max(DataNumWebElementComparator())
        val dataNum = lastElement.get().getAttribute("data-num")
        val connectWayXpath = "//div[contains(@class,'container') and @data-num='$dataNum']//span[@class='placeholder']/.."
        driver.findElement(By.xpath(connectWayXpath)).click()
        driver.findElement(By.xpath("//div[@data-num=$dataNum]//button[@title='Facebook']")).click()
        driver.findElement(By.xpath("//input[@name='contact-$dataNum-value']")).sendKeys(value)
    }

    fun checkContactWithValue(value: String) = assertThat(
        driver.findElements(
            By.xpath("//input[@value='$value']/../../../..//div[contains(text(),'Facebook')]")
        ).size
    ).isEqualTo(1)

    fun deleteOldContacts() {
        val js = driver as JavascriptExecutor
        driver.findElements(By.xpath(buttonDeleteContactXpath))
            .forEach { webElement ->
                js.executeScript("arguments[0].click();", webElement)
            }
    }

    fun save() = driver.findElement(By.xpath(buttonSaveAndContinueXpath)).click()

}
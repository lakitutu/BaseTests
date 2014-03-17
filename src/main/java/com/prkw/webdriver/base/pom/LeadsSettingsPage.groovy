package com.prkw.webdriver.base.pom

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

/**
 * Created by SG0210921 on 16.03.14.
 */
class LeadsSettingsPage extends AbstractBasePage{
    static final LEADS_SETTINGS_STATUS_HREF = '#lead-status'
    static final LEADS_SETTINGS_STATUS_EDIT_BUTTON_CSS = 'div#lead-status.tab-pane div.named-objects-list div div.control-group div.controls div.btn-toolbar button.btn'
    static final EDIT_STATUS_SAVE_BUTTON_CSS = 'div.named-objects-list div div.item form fieldset div.control-group div.controls button.btn'
    static final STATUS_NAME_CSS = 'div#lead-status.tab-pane div.named-objects-list div div.item form fieldset div.control-group div.controls input#name.input-xlarge'

    LeadsSettingsPage(WebDriver driver,WebDriverWait wait) {
        this.driver = driver
        this.wait = wait
    }

    def changeStatusName(String oldName, String newName){
        goToLeadStatuses()
        driver.findElement(By.xpath("//div[@class='control-group item']/label/h4[text()='$oldName']/../../div/div/button")).click()
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(EDIT_STATUS_SAVE_BUTTON_CSS)))
        def statusName = driver.findElement(By.cssSelector(STATUS_NAME_CSS))
        statusName.clear()
        statusName.sendKeys(newName)
        driver.findElement(By.cssSelector(EDIT_STATUS_SAVE_BUTTON_CSS)).click()
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("$LEADS_SETTINGS_STATUS_EDIT_BUTTON_CSS")))
        //sleep(10000)
    }

    def private goToLeadStatuses(){
        driver.findElement(By.cssSelector("a[href*='$LEADS_SETTINGS_STATUS_HREF']")).click()
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("$LEADS_SETTINGS_STATUS_EDIT_BUTTON_CSS")))
    }
}

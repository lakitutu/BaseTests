package com.prkw.webdriver.base.pom

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

/**
 * Created by SG0210921 on 16.03.14.
 */
class SettingsPage extends AbstractBasePage{
    static final SETTINGS_PAGE_LEADS_HREF = '/settings/leads'
    static final LEADS_SETTINGS_STATUS_HREF = '#lead-status'

    SettingsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver
        this.wait = wait
    }

    LeadsSettingsPage goToLeadsSettings(){
        driver.findElement(By.cssSelector("a[href*='$SETTINGS_PAGE_LEADS_HREF']")).click()
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href*='$LEADS_SETTINGS_STATUS_HREF']")))
        new LeadsSettingsPage(driver, wait)
    }
}

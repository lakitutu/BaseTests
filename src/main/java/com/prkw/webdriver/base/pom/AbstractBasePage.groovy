package com.prkw.webdriver.base.pom

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

/**
 * Created by SG0210921 on 16.03.14.
 */
abstract class AbstractBasePage {
    static final LEADS_PAGE_BUTTON_HREF = '/leads'
    static final LEADS_NEW_BUTTON_HREF = '/leads/new'
    static final SETTINGS_PAGE_BUTTON_HREF = '#user-dd'
    static final SETTINGS_PAGE_LEADS_HREF = '/settings/leads'
    static final SETTINGS_PAGE_SETTINGS_PROFILE_HREF = '/settings/profile'
    static final USER_SETTINGS_DROPDOWN_HREF = '#user-dd'
    static final LOGOUT_CLICKABLE_HREF = 'https://core.futuresimple.com/users/logout'

    WebDriver driver
    WebDriverWait wait

    LeadsPage goToLeadsPage(){
        driver.findElement(By.cssSelector("a[href*='$LEADS_PAGE_BUTTON_HREF']")).click()
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href*='$LEADS_NEW_BUTTON_HREF']")))
        new LeadsPage(driver, wait)
    }

    SettingsPage goToSettingsPage(){
        driver.findElement(By.cssSelector("a[href*='$SETTINGS_PAGE_BUTTON_HREF']")).click()
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href*='$SETTINGS_PAGE_SETTINGS_PROFILE_HREF']")))

        driver.findElement(By.cssSelector("a[href*='$SETTINGS_PAGE_SETTINGS_PROFILE_HREF']")).click()
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href*='$SETTINGS_PAGE_LEADS_HREF']")))

        new SettingsPage(driver, wait)
    }

    def logOut(){
        driver.findElement(By.cssSelector("a[href*='$USER_SETTINGS_DROPDOWN_HREF']")).click()
        driver.findElement(By.cssSelector("a[href*='$LOGOUT_CLICKABLE_HREF']")).click()
        driver.close()
        driver.quit()
    }
}

package com.prkw.webdriver.base.pom

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.WebDriverWait

/**
 * Created by SG0210921 on 16.03.14.
 */
class LeadPropertiesPage extends AbstractBasePage{

    LeadPropertiesPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver
        this.wait = wait
    }

    String getLeadStatus(){
        driver.findElement(By.className('lead-status')).getText()
    }
}

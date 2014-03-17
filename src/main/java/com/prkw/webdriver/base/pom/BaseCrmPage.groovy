package com.prkw.webdriver.base.pom

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

/**
 * Created by SG0210921 on 16.03.14.
 */
class BaseCrmPage extends AbstractBasePage{
    static final BASE_CRM_TITLE = 'Base CRM'


    BaseCrmPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver
        this.wait = wait
        if(!driver.getTitle().equals(BASE_CRM_TITLE)) {
            throw new IllegalStateException("This is not $BASE_CRM_TITLE in page, current page is: "
                    +driver.getTitle())
        }
    }
}

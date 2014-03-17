package com.prkw.webdriver.base.pom

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

/**
 * Created by SG0210921 on 15.03.14.
 */
public class LoginToBasePage {
    static final BASE_LOGIN_TITLE = 'Login to Base'
    static final EMAIL_ID = 'user_email'
    static final PASSWORD_ID = 'user_password'
    static final WAIT_UNTIL_ELEMENT_TO_BE_CLICKABLE = 'topbar'


    WebDriver driver
    WebDriverWait wait

    LoginToBasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver
        this.wait = wait

        if(!driver.getTitle().equals(BASE_LOGIN_TITLE)) {
            throw new IllegalStateException("This is not sign in page, current page is: "
                    +driver.getTitle())
        }
    }

    public BaseCrmPage loginValidCredentials(String login, String password){

        def loggingEmail = driver.findElement(By.id(EMAIL_ID))
        loggingEmail.sendKeys(login)

        def loggingPassword =driver.findElement(By.id(PASSWORD_ID))
        loggingPassword.sendKeys(password)
        loggingPassword.sendKeys(Keys.RETURN)

        wait.until(ExpectedConditions.elementToBeClickable(By.id(WAIT_UNTIL_ELEMENT_TO_BE_CLICKABLE)))
        new BaseCrmPage(driver, wait)
    }
}

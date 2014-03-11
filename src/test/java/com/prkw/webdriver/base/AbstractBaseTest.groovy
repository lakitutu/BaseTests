package com.prkw.webdriver.base


import junit.framework.Assert
import org.apache.commons.configuration.Configuration
import org.apache.commons.configuration.PropertiesConfiguration
import org.apache.log4j.Logger
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.annotations.AfterMethod
import org.testng.annotations.AfterSuite
import org.testng.annotations.BeforeMethod
import org.testng.annotations.BeforeSuite

import java.lang.reflect.Method

/**
 * Created by SG0210921 on 09.03.14.
 */
abstract class AbstractBaseTest {
    public static final Logger LOG = Logger.getLogger('base.test')
    static final BASE_LOGIN_URL = 'https://core.futuresimple.com/sales/users/login'
    static final EMAIL_ID = 'user_email'
    static final PASSWORD_ID = 'user_password'
    //static final SIGN_IN_BUTTON_CLASS = "btn btn-large btn-primary"
    static final WAIT_UNTIL_ELEMENT_TO_BE_CLICKABLE = 'topbar'
    static final USER_SETTINGS_DROPDOWN_HREF = '#user-dd'
    static final LOGOUT_CLICKABLE_HREF = 'https://core.futuresimple.com/users/logout'
    static final LOGGED_BASE_NAME = 'Base CRM'

    WebDriver driver


    @BeforeSuite
    protected void connectToBase(){
        Logger LOG = Logger.getLogger("base.test")
        LOG.info("Logging into Base")

        Configuration config = new PropertiesConfiguration("BaseCredentials.properties")
        def email = config.getString("base.email")
        def password = config.getString("base.password")

        driver = new FirefoxDriver();
        driver.get(BASE_LOGIN_URL)

        def loggingEmail = driver.findElement(By.id(EMAIL_ID))
        loggingEmail.sendKeys(email)

        def loggingPassword =driver.findElement(By.id(PASSWORD_ID))
        loggingPassword.sendKeys(password)
        loggingPassword.sendKeys(Keys.RETURN)


        WebDriverWait wait = new WebDriverWait(driver,20)
        wait.until(ExpectedConditions.elementToBeClickable(By.id(WAIT_UNTIL_ELEMENT_TO_BE_CLICKABLE)))

        Assert.assertEquals(driver.getTitle(), LOGGED_BASE_NAME)
        LOG.info("Logging into Base, succeed")
        //driver.close()
    }

    @AfterSuite
    protected void closeBaseConnection(){

        driver.findElement(By.cssSelector("a[href*='$USER_SETTINGS_DROPDOWN_HREF']")).click()
        driver.findElement(By.cssSelector("a[href*='$LOGOUT_CLICKABLE_HREF']")).click()
        driver.close()
        driver.quit()
        LOG.info("Connection to Base closed")
    }

    @BeforeMethod(alwaysRun = true)
    protected void identifyStart(Method method) {// "Borrowed code, I am not master of Reflections framework
        LOG.info("Running test: " + method.getDeclaringClass().getCanonicalName() + ":" + method.getName())
    }

    @AfterMethod(alwaysRun = true)
    protected void identifyEnd(Method method) {
        LOG.info("Ending test: " + method.getDeclaringClass().getCanonicalName() + ":" + method.getName())
    }
}

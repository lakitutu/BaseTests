package com.prkw.webdriver.base.settingsTests

import com.prkw.webdriver.base.AbstractBaseTest
import com.prkw.utils.CustomFieldsHelper
import junit.framework.Assert
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.annotations.Test

/**
 * Created by SG0210921 on 09.03.14.
 */
class LeadsTest extends AbstractBaseTest{
    static final LEADS_NEW_URL = 'https://app.futuresimple.com/leads/new'
    static final CUSTOM_FIELDS_URL = 'https://app.futuresimple.com/settingsTests/leads/custom-fields'
    static final CONTACTS_URL = 'https://app.futuresimple.com/settings/contacts'
    static final CUSTOM_FIELDS_ID = 'custom-fields'
    static final CUSTOM_FIELDS_HREF = '/settings/leads'
    static final CONTACTS_HREF = '/settings/contacts'

    static final TIMEOUT_IN_SECONDS = 20
    def map = [:]

    @Test(enabled=false)
    public void  logIn(){
        LOG.info("Log in test")
    }

    @Test
    public void  getCustomFieldsFromSettings(){
        driver.get(CONTACTS_URL)// TODO - hook - looks like Custom Fields are not loading if accessed directly

        WebDriverWait wait = new WebDriverWait(driver,TIMEOUT_IN_SECONDS)
        wait.until(ExpectedConditions.elementToBeClickable(By.id("sidebar")))

        driver.findElement(By.cssSelector("a[href*='$CUSTOM_FIELDS_HREF']")).click()
        driver.findElement(By.cssSelector("a[href*='$CONTACTS_HREF']")).click()
        driver.findElement(By.cssSelector("a[href*='$CUSTOM_FIELDS_HREF']")).click()

        WebDriverWait waitForCustomFields = new WebDriverWait(driver,TIMEOUT_IN_SECONDS)
        waitForCustomFields.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn.btn-mini.edit")))

        def html = driver.getPageSource()
        def resultMap = CustomFieldsHelper.getCustomFieldsNamesTypesFromCustomFieldsUrl(html)

        Assert.assertEquals(resultMap.size(),7)
        map = resultMap

        resultMap.each{
            //LOG.info("key="+it.key+"value="+it.value)
        }
    }

    @Test(dependsOnMethods = "getCustomFieldsFromSettings")
    public void  compareCustomFieldsFromSettingsWithLeadsNew(){

        driver.get(LEADS_NEW_URL)

        WebDriverWait waitForLeadsNew = new WebDriverWait(driver,TIMEOUT_IN_SECONDS)
        waitForLeadsNew.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".cancel.btn.btn-large")))

        def html = driver.getPageSource()

        def resultSet = CustomFieldsHelper.getCustomFieldsNamesFromLeadsNewUrl(html)

        Assert.assertEquals(resultSet.size(), 7)
        Assert.assertTrue(resultSet == map.keySet())
    }

}

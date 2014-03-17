package com.prkw.webdriver.base.pom

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

/**
 * Created by SG0210921 on 16.03.14.
 */
class LeadsPage extends AbstractBasePage{
    static final LEADS_TITLE = 'Base CRM: Leads'
    static final LEADS_NEW_BUTTON_HREF = '/leads/new'
    static final NEW_LEAD_SAVE_BUTTON_CLASSES = '.save.btn.btn-large.btn-primary'
    static final CREATED_LEAD_DELETE_BUTTON_CLASSES = '.btn.delete'
    static final LEADS_PAGE_BUTTON_HREF = '/leads'


    static final FIRST_NAME_ID = 'lead-first-name'
    static final LAST_NAME_ID = 'lead-last-name'


    LeadsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver
        this.wait = wait
        if(!driver.getTitle().equals(LEADS_TITLE)) {
            throw new IllegalStateException("This is not $LEADS_TITLE page, current page is: "
                    +driver.getTitle())
        }
    }

    LeadsPage addLeadStart(){
        driver.findElement(By.cssSelector("a[href*='$LEADS_NEW_BUTTON_HREF']")).click()
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("$NEW_LEAD_SAVE_BUTTON_CLASSES")))
        this
    }

    LeadsPage addLeadFirstName(String name){
        def addName =driver.findElement(By.id(FIRST_NAME_ID))
        addName.sendKeys(name)
        this
    }

    LeadsPage addLeadLastName(String name){
        def addName =driver.findElement(By.id(LAST_NAME_ID))
        addName.sendKeys(name)
        this
    }

    LeadPropertiesPage addLeadSave(){
        driver.findElement(By.cssSelector("$NEW_LEAD_SAVE_BUTTON_CLASSES")).click()
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("$CREATED_LEAD_DELETE_BUTTON_CLASSES")))
        new LeadPropertiesPage(driver, wait)
    }

    def backToLeads(){
        driver.findElement(By.cssSelector("a[href*='$LEADS_PAGE_BUTTON_HREF']")).click()
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href*='$LEADS_NEW_BUTTON_HREF']")))
    }

    LeadPropertiesPage goToLeadByLastName(String lastName){
        driver.findElement(By.xpath("//div/h3/a[text()='$lastName']")).click()
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("$CREATED_LEAD_DELETE_BUTTON_CLASSES")))
        new LeadPropertiesPage(driver, wait)
    }
}

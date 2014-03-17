package com.prkw.webdriver.base.settingsTests

import com.prkw.webdriver.base.AbstractBaseTest
import com.prkw.webdriver.base.pom.BaseCrmPage
import com.prkw.webdriver.base.pom.LeadPropertiesPage
import com.prkw.webdriver.base.pom.LeadsPage
import com.prkw.webdriver.base.pom.LeadsSettingsPage
import com.prkw.webdriver.base.pom.SettingsPage
import junit.framework.Assert
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.annotations.Test
import sun.launcher.resources.launcher

import java.util.concurrent.TimeUnit

/**
 * Created by SG0210921 on 16.03.14.
 */
class LeadStatusTest extends AbstractBaseTest{

    @Test
    public void  createNewLead(){
        LeadsPage leadsPage = baseCrmPage.goToLeadsPage()

        def lastName = "Smith"+(new java.util.Date().getTime())

        LeadPropertiesPage leadPropertiesPage = leadsPage.addLeadStart()
                .addLeadLastName(lastName)
                .addLeadSave()

        Assert.assertEquals(leadPropertiesPage.getLeadStatus(), 'New')

    }

    @Test
    public void  createNewLeadAndChangeStatus(){
        def oldStatus = 'New'
        def newStatus = 'AnotherState'

        LeadsPage leadsPage = baseCrmPage.goToLeadsPage()

        def lastName = "Smith"+(new java.util.Date().getTime())

        LeadPropertiesPage leadPropertiesPage = leadsPage.addLeadStart()
                .addLeadLastName(lastName)
                .addLeadSave()

        Assert.assertEquals(leadPropertiesPage.getLeadStatus(), oldStatus)

        SettingsPage settingsPage = baseCrmPage.goToSettingsPage()
        LeadsSettingsPage leadsSettingsPage = settingsPage.goToLeadsSettings()

        leadsSettingsPage.changeStatusName(oldStatus, newStatus)
        LeadPropertiesPage leadPropertiesPageVerifyStatus = leadsSettingsPage.goToLeadsPage().goToLeadByLastName(lastName)
        sleep(3000)
        try{
            Assert.assertEquals(leadPropertiesPageVerifyStatus.getLeadStatus(), newStatus)
        } finally {
            leadPropertiesPage.goToSettingsPage()
                    .goToLeadsSettings()
                    .changeStatusName(newStatus, oldStatus)
        }
    }
}

package com.aperov.teststeps;

import com.aperov.conf.ConfigProperties;
import com.aperov.pageobjects.economicCalendar.EconomicCalendarView;
import com.aperov.pageobjects.xm.XMView;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Copyright (c) 2022.
 * Test steps
 *
 * @author Aleksei Perov
 * @version 1.0
 * @since 1.0
 */

public class EconomicCalendarTestSteps extends EconomicCalendarView {
    private static final ConfigProperties CONFIG_PROPERTIES = ConfigProperties.getInstance();

    @Given("Open browser with {string} resolution")
    public void openBrowserWithResolution(String resolution) {
        System.setProperty("-Dorg.slf4j.simpleLogger.defaultLogLevel", "debug");
        Configuration.timeout = CONFIG_PROPERTIES.getMaxWaitTime();
        ChromeOptions options = new ChromeOptions();
        if (resolution.equalsIgnoreCase("fullScreen")) {
            options.addArguments("start-maximized");
            Configuration.browserSize = null;
        } else {
            Configuration.browserSize = resolution;
        }
        Map<String, Object> preferences = new Hashtable<>();
        options.setExperimentalOption("prefs", preferences);
        preferences.put("plugins.plugins_enabled", "Chrome PDF Viewer");
        Configuration.browserCapabilities = options;
        Configuration.reportsFolder = "target/results";
        Configuration.browser = CONFIG_PROPERTIES.getDriverType();
        Configuration.baseUrl = CONFIG_PROPERTIES.getUIEnvAddress();
        Configuration.downloadsFolder = "target/downloads";
        Configuration.pageLoadTimeout = 120000; //because xm.com in my case opens too slow sometimes
        open(CONFIG_PROPERTIES.getUIEnvAddress());
    }

    @Then("Accept cookies and terms")
    public void acceptCookiesAndTerms() {
        XMView xmView = new XMView();
        xmView.acceptCookies();
    }

    @Then("Clear browser")
    public void clearBrowser() {
        Selenide.clearBrowserCookies();
        Selenide.closeWebDriver();
    }

    @Then("Check that browser width is equal to {int} and height to {int}")
    public void checkThatBrowserWidthIsEqualToAndHeightTo(int expectedWidth, int expectedHeight) {
        Dimension browserSize = getWebDriver().manage().window().getSize();
        int width = browserSize.getWidth();
        int height = browserSize.getHeight();
        assertEquals(expectedWidth, width);
        assertEquals(expectedHeight, height);
    }

    @When("Click on {string} nav button")
    public void clickOnNavButton(String navItemName) {
        XMView view = new XMView();
        view.clickOnNavBtnByName(navItemName);
    }

    @And("Click on {string} in dropdown nav menu")
    public void clickOnInDropdownNavMenu(String navItemName) {
        XMView view = new XMView();
        view.clickOnNavDropDownMenuItem(navItemName);
        getWebDriver().switchTo().frame(0);
    }

    @Then("Move slider to the right")
    public void moveSliderToTheRight() throws InterruptedException {
        dateSlider.scrollTo();
        dateSlider.shouldBe(interactable);
        dateSlider.sendKeys(Keys.ARROW_RIGHT);

    }

    @Then("Pick yesterday date")
    public void pickYesterdayDate() throws InterruptedException {
        //waiting for table to be loaded to avoid using explicit sleep as
        //slider has glitch that rolls back its position if it was moved to quick
        pastEventsBtn.shouldBe(visible);
        chooseYesterdaysDateInCalendar();
    }

    @Then("Check that label in section num {int} date is {int} days from today")
    public void checkThatLabelInSectionNumDateIsDaysFromToday(int sectionNum, int dayShiftAmount) {
        LocalDate today = LocalDate.now();
        LocalDate date = today.plusDays(dayShiftAmount);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MMM dd", Locale.ENGLISH);
        String dateStr = date.format(formatter);
        String dateInSectionHeader = getTableSectionTitleDateText(sectionNum);
        assertEquals(dateStr, dateInSectionHeader);
    }

    @Then("Slider position label equals to {string}")
    public void sliderPositionLabelEqualsTo(String expectedText) {
        sliderPositionLabel.shouldHave(text(expectedText));
    }

    @Then("Check that label in section num {int} date is Monday of Week number {int}")
    public void checkThatLabelInSectionNumDateIsMondayOfWeekNumber(int sectionNum, int weeksShiftAmount) {
        LocalDate today = LocalDate.now();
        LocalDate monday = today.with(DayOfWeek.MONDAY).plusWeeks(weeksShiftAmount);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MMM dd", Locale.ENGLISH);
        String dateStr = monday.format(formatter);
        String dateInSectionHeader = getTableSectionTitleDateText(sectionNum);
        assertEquals(dateStr, dateInSectionHeader);
    }

    @Then("Check disclaimer has expected text with the link")
    public void checkDisclaimerHasExpectedTextWithTheLink() throws IOException {
        getWebDriver().switchTo().defaultContent();
        disclaimer.scrollTo();
        String expText = FileUtils.readFileToString(new File("src/test/resources/texts/disclaimer.txt"), StandardCharsets.UTF_8);
        assertEquals(expText.trim(), disclaimer.text().trim());
    }

    @Then("Open risk warning page by link from disclaimer")
    public void openRiskWarningPageByLinkFromDisclaimer() {
        disclaimerLinkHere.shouldBe(visible).click();
    }

    @Then("Open pdf {string} by link")
    public void openPdfByLink(String pdfName) {
        openPdfLinkByName(pdfName);
    }

    @Then("Check that additional PDF tab is opened and url has {string} in it")
    public void checkThatAdditionalTabIsOpenedAndUrlHasInIt(String urlPart) throws InterruptedException {
        Set<String> handles = WebDriverRunner.getWebDriver().getWindowHandles();
        List<String> handleList = new ArrayList<>(handles);
        WebDriverRunner.getWebDriver().switchTo().window(handleList.get(1));
        String pageSource = WebDriverRunner.getWebDriver().getPageSource();
        assertTrue(pageSource.contains("type=\"application/pdf\""));
    }

    @When("Open burger menu")
    public void openBurgerMenu() {
        burgerMenu.shouldBe(visible).click();
    }

    @And("Under opened menu {string} Click on {string} option")
    public void underOpenedMenuClickOnOption(String menuName, String optionName) {
        XMView view = new XMView();
        view.clickOnOptionUnderMenuByName(menuName, optionName);
    }

    @Then("Open calendar in mobile version")
    public void openCalendarInMobileVersion() {
        openCalendar();
    }

    @And("Switch to frame num {int}")
    public void switchToFrameNum(int num) {
        getWebDriver().switchTo().frame(num);
    }

    @Then("Check that page is displayed")
    public void checkThatPageIsDisplayed() {
        assertTrue(isPageLogoDisplayed());
    }
}
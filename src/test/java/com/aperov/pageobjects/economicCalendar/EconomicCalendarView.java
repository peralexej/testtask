package com.aperov.pageobjects.economicCalendar;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Copyright (c) 2022.
 * Class for operation with elements (page view)
 *
 * @author Aleksei Perov
 * @version 1.0
 * @since 1.0
 */

public class EconomicCalendarView extends EconomicCalendarContainer {

    @Override
    public boolean isPageLogoDisplayed() {
        SelenideElement logoImage = $x("//div[contains(@class, 'navbar-header')]//a[@class='navbar-brand logo']");
        return logoImage.getSize().width > 0 && logoImage.getSize().height > 0;
    }

    public void chooseYesterdaysDateInCalendar() {
        //according to the task "Click <Yesterday> button and check that date is correct."
        //I assumed that there is some particular button for yesterday's date
        //I didn't find anything like that, so, I made this code to choose yesterday's date
        if (calendarTodayDateBtn.getText().trim().equals("1")) {
            prevMonthArrowBtn.click();
            listOfCalendarDaysBtns.get(listOfCalendarDaysBtns.size() - 1).click();
            return;
        }
        //here I decided to show my xpath skills :)
        //actually we can just get list of elements -> define which one is today -> click on the previous in the list
        SelenideElement yesterday = $x("//div[contains(@class, 'mat-calendar-body-today')]" +
                "/ancestor::*[@role='gridcell']/preceding-sibling::td[1]//div");
        yesterday.scrollTo().shouldBe(visible);
        executeJavaScript("arguments[0].click();", yesterday);
    }

    public String getTableSectionTitleDateText(int numberOfSection) {
        return $x("//span[contains(@class, 'tc-economic-calendar-item-header-left-title')]["
                + numberOfSection + "]")
                .shouldBe(visible)
                .getText()
                .trim();
    }

    public void openPdfLinkByName(String name) {
        SelenideElement el = $x("//a[contains(.,'here')][contains(@href, '" + name + "')]");
        el.shouldBe(visible);
        executeJavaScript("arguments[0].click();", el);
    }

    public void openCalendar() {
        getWebDriver().switchTo().frame(0);
        SelenideElement el = $x("//span[@aria-label='Show time filter']");
        el.shouldBe(visible);
        executeJavaScript("arguments[0].click();", el);
    }
}

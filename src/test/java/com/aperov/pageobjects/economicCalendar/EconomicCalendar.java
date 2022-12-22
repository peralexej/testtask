package com.aperov.pageobjects.economicCalendar;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
/**
 * Copyright (c) 2022.
 * Calendar interface
 *
 * @author Aleksei Perov
 * @version 1.0
 * @since 1.0
 */
public interface EconomicCalendar {
    SelenideElement calendarRootElement = $(By.xpath("//tc-time-filter-container"));
    boolean isPageLogoDisplayed();
}

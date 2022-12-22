package com.aperov.pageobjects.economicCalendar;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Copyright (c) 2022.
 * Class container of page elements
 *
 * @author Aleksei Perov
 * @version 1.0
 * @since 1.0
 */

public abstract class EconomicCalendarContainer implements EconomicCalendar {
    public final SelenideElement dateSlider = calendarRootElement.$("mat-slider");
    public final SelenideElement sliderPositionLabel = $x("//tc-time-filter-container//div[@class='ng-star-inserted']");
    public final SelenideElement disclaimer = $x("//div[contains(@class, 'p-200')]");
    public final SelenideElement disclaimerLinkHere = disclaimer.$("a");
    public final SelenideElement pastEventsBtn = $x("//span[contains(., 'Past Events')]");
    public final SelenideElement calendarTodayDateBtn = $x("//div[contains(@class, 'mat-calendar-body-today')]");
    public final SelenideElement prevMonthArrowBtn = $x("//button[@aria-label='Previous month']");
    public final SelenideElement burgerMenu = $x("//button[@class='toggleLeftNav']");
    public final ElementsCollection listOfCalendarDaysBtns = $$x("//button[@class='mat-calendar-body-cell']");

}

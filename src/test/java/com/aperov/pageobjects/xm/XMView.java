package com.aperov.pageobjects.xm;


import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
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
public class XMView extends XMContainer {
    public void clickOnNavBtnByName(String name) {
        SelenideElement navBtn = $(By.xpath("//li[@class='main_nav_" + name + "']"));
        navBtn.shouldBe(visible).click();
    }

    public void clickOnNavDropDownMenuItem(String name) {
        SelenideElement navBtn = $(By.xpath("//a[contains(@href, '" + name + "')]"));
        navBtn.scrollTo().shouldBe(visible).click();
    }

    public void clickOnOptionUnderMenuByName(String menuName, String optionName) {
        getWebDriver().switchTo().defaultContent();
        SelenideElement navBtn = $(By.xpath("//div[@id='" + menuName + "']//a[contains(@href, '" + optionName + "')]"));
        executeJavaScript("arguments[0].click();", navBtn);
    }

    public void acceptCookies() {
        cookiesModal.shouldBe(visible);
        cookiesAcceptBtn.shouldBe(visible).click();
        cookiesModal.shouldBe(hidden);
    }
}

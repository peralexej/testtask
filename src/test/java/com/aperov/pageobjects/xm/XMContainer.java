package com.aperov.pageobjects.xm;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

/**
 * Copyright (c) 2022.
 * Class container of page elements
 *
 * @author Aleksei Perov
 * @version 1.0
 * @since 1.0
 */
public class XMContainer {
    public final SelenideElement cookiesModal = $x("//div[@class='cookie-modal__defaultBlock']");
    public final SelenideElement cookiesAcceptBtn = $x("//button[contains(@class, 'js-acceptDefaultCookie')]");


}

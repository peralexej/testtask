@UI
Feature: UI tests for Economic Calendar

  Scenario: Check in full monitor resolution
    Given Open browser with "fullScreen" resolution
    Then Check that page is displayed
    Then Accept cookies and terms
    When Click on "research" nav button
    And Click on "economicCalendar" in dropdown nav menu
    When Pick yesterday date
    Then Slider position label equals to "Custom"
    Then Check that label in section num 1 date is -1 days from today
    Then Move slider to the right
    Then Slider position label equals to "Today"
    # put 0 here if you want to use current date
    Then Check that label in section num 1 date is 0 days from today
    Then Move slider to the right
    Then Slider position label equals to "Tomorrow"
    Then Check that label in section num 1 date is 1 days from today
    Then Move slider to the right
    Then Slider position label equals to "This Week"
    # put 0 here if you want to use current week's Monday, so if we would want to continue to the Next week we can reuse it
    Then Check that label in section num 1 date is Monday of Week number 0
    Then Check disclaimer has expected text with the link
    Then Open risk warning page by link from disclaimer
    Then Open pdf "XM-Risk-Disclosures-for-Financial-Instruments.pdf" by link
    Then Check that additional PDF tab is opened and url has "XM-Risk-Disclosures-for-Financial-Instruments.pdf" in it
    Then Clear browser


  Scenario: Check in 1024x768 browser resolution
    Given Open browser with "1024x768" resolution
    Then Check that browser width is equal to 1024 and height to 768
    Then Accept cookies and terms
    When Click on "research" nav button
    And Click on "economicCalendar" in dropdown nav menu
    When Pick yesterday date
    Then Slider position label equals to "Custom"
    Then Check that label in section num 1 date is -1 days from today
    Then Move slider to the right
    Then Slider position label equals to "Today"
    # put 0 here if you want to use current date
    Then Check that label in section num 1 date is 0 days from today
    Then Move slider to the right
    Then Slider position label equals to "Tomorrow"
    Then Check that label in section num 1 date is 1 days from today
    Then Move slider to the right
    Then Slider position label equals to "This Week"
    # put 0 here if you want to use current week's Monday, so if we would want to continue to the Next week we can reuse it
    Then Check that label in section num 1 date is Monday of Week number 0
    Then Check disclaimer has expected text with the link
    Then Open risk warning page by link from disclaimer
    Then Open pdf "XM-Risk-Disclosures-for-Financial-Instruments.pdf" by link
    Then Check that additional PDF tab is opened and url has "XM-Risk-Disclosures-for-Financial-Instruments.pdf" in it
    Then Clear browser

  Scenario: Check in 800x600 browser resolution
    Given Open browser with "800x600" resolution
    Then Check that browser width is equal to 800 and height to 600
    Then Accept cookies and terms
    When Open burger menu
    And Click on "researchMenu" in dropdown nav menu
    And Under opened menu "researchMenu" Click on "economicCalendar" option
    Then Open calendar in mobile version
    When Pick yesterday date
    Then Slider position label equals to "Custom"
    Then Check that label in section num 1 date is -1 days from today
    Then Move slider to the right
    Then Slider position label equals to "Today"
    # put 0 here if you want to use current date
    Then Check that label in section num 1 date is 0 days from today
    Then Move slider to the right
    Then Slider position label equals to "Tomorrow"
    Then Check that label in section num 1 date is 1 days from today
    Then Move slider to the right
    Then Slider position label equals to "This Week"
    # put 0 here if you want to use current week's Monday, so if we would want to continue to the Next week we can reuse it
    Then Check that label in section num 1 date is Monday of Week number 0
    Then Check disclaimer has expected text with the link
    Then Open risk warning page by link from disclaimer
    Then Open pdf "XM-Risk-Disclosures-for-Financial-Instruments.pdf" by link
    Then Check that additional PDF tab is opened and url has "XM-Risk-Disclosures-for-Financial-Instruments.pdf" in it
    Then Clear browser


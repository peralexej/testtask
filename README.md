To run these tests: 'mvn clean test' or just compile RunTests.java in your IDEA<br/>
<br/>
Use tags under RunTests.java if you're running it manually and want to run api or ui tests separately (example is commented)<br/>
<br/>
It was made by using java 1.8, Selenide, Cucumber and RestAssured<br/>
<br/>
Both API and UI Test cases stored under features folder and divided by features<br/>
<br/>
In this file: 'config.properties' you can set URL of environment, choose a driver type and set maximum waiting time for selenide (in seconds)<br/>
<br/>
Cucumber html report (pretty) will be generated in target/cucumber<br/>
<br/>
Screenshots and html pages of failed tests stored in target/results<br/>
<br/>

# selenium-java

## Test Plan
This repo contains tests designed to verify a user can:
 * Navigate to the **used gear** section of the REI site.
 * Add items to their cart.
 * Remove items from the cart.
 * Enter shipping info for all shipping fields.
 * Arrive at the billing screen.
 
 ## Installation
 These instructions are for a Selenium Java Maven project run against Firefox with the IntelliJ IDE.
 
 Navigate to the Selenium site and download and extract the latest Selenium Webdriver:
 https://selenium-release.storage.googleapis.com/3.141/selenium-java-3.141.59.zip
 
 Create a new IntelliJ Maven project.  Select *File > Project Structure > Modules >
 Dependencies*, select the *plus* sign and select to add a JAR.  Navigate to the Selenium
 files and select the Selenium JAR file.  You will also need Java 11, and Apache Maven 3.6.
 
 To run the test simply select `ShoppingTest`, right click, and select run.
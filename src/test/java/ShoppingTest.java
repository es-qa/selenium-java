import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShoppingTest {

    public static void main(String[] args) {

//setting the driver executable
        System.setProperty("webdriver.firefox.driver", ".\\Driver\\firefox.exe");
//Initiating your firefox driver
        WebDriver driver=new FirefoxDriver();
//Setup explicit waits
        WebDriverWait wait = new WebDriverWait(driver, 5);
//Applied wait time
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//maximize window
        driver.manage().window().maximize();
//open browser with desired URL
        driver.get("https://www.rei.com/used");

        //Navigate to Gear
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Gear"))).click();
        driver.findElement(By.xpath("//a[h6='Gear']")).click();

        //Get list of products and select first product.
        int i = 0;
        List<WebElement> productList = driver.findElements(By.className("TileItem"));
        productList.get(i).click();
        i++;

        //Verify item can be added to cart
        itemCheck(i, driver);
        driver.findElement(By.className("add-to-cart")).click();
        //Verify item was added, and if not, select options and add.
        isAdded(driver);

        //Navigate back to gear
        driver.navigate().back();

        //Refresh list and select 2nd item
        List<WebElement> productList2ndItem = driver.findElements(By.className("TileItem"));
        productList2ndItem.get(i).click();
        i++;

        //Verify item can be added to cart
        itemCheck(i, driver);
        driver.findElement(By.className("add-to-cart")).click();
        //Verify item was added, and if not, select options and add.
        isAdded(driver);

        //View cart and remove bottom item
        driver.findElement(By.className("cart")).click();
        List<WebElement> cartList = driver.findElements(By.className("image-wrap"));
        WebElement itemToRemove = cartList.get(0);
        itemToRemove.findElement(By.xpath("//button[contains(text(), 'Remove')]")).click();

        //Checkout
        driver.findElement(By.className("checkout-button")).click();

        //Shipping
        driver.findElement(By.name("email")).sendKeys("bob@vance.com");
        driver.findElement(By.name("emailConfirm")).sendKeys("bob@vance.com");
        driver.findElement(By.name("firstName")).sendKeys("bob");
        driver.findElement(By.name("lastName")).sendKeys("vance");
        driver.findElement(By.name("shippingAddressLine1")).sendKeys("270 BEECH AVE");
        driver.findElement(By.name("shippingAddressLine2")).sendKeys("APT A");
        driver.findElement(By.name("shippingAddressCity")).sendKeys("CHULA VISTA");
        driver.findElement(By.xpath("//option[contains(text(), 'California')]")).click();
        driver.findElement(By.name("shippingAddressZip")).sendKeys("91910-3548");
        driver.findElement(By.name("phone")).sendKeys("619-330-2204");
        driver.findElement(By.xpath("//button[contains(text(), 'Continue to Billing')]")).click();
        driver.findElement(By.xpath(("//button[contains(text(), 'Next')]"))).click();

        //Assert billing page loads
        assert (driver.getCurrentUrl().contains("https://www.rei.com/used/checkout/billing"));

        //closing the browser
        driver.close();
    }


    public static void itemCheck(int i, WebDriver driver) {

        try {
            WebElement disabled = driver.findElement(By.xpath("//button[contains(text(), \"In someone's cart\")]"));
            while (disabled.isDisplayed()) {
                driver.navigate().back();
                List<WebElement> productListAvailable = driver.findElements(By.className("TileItem"));
                WebElement tryNextItem = productListAvailable.get(i);
                tryNextItem.click();
                i++;
                disabled = driver.findElement(By.xpath("//button[contains(text(), \"In someone's cart\")]"));
            }

        }catch (NoSuchElementException e) {
            System.out.println("Item Available");
        }
    }

    public static void isAdded(WebDriver driver) {

        try {
            WebElement condition = driver.findElement(By.xpath("//strong[contains(text(), 'Please select a condition')]"));

            if (condition.isDisplayed()) {
                driver.findElement(By.name("condition")).click();
                driver.findElement(By.className("add-to-cart")).click();
            }
        }catch (NoSuchElementException e) {
            System.out.println("Condition already selected");
        }
    }
}
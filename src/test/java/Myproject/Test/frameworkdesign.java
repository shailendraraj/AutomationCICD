package Myproject.Test;

import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Myproject.pageobjects.Landingpage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class frameworkdesign {

	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		String product_name = "ZARA COAT 3";
		WebDriverManager.firefoxdriver().setup();
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		Landingpage LandingPage = new Landingpage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("testdoe123@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Jhondoe@123");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream().
		filter(product -> product.findElement(By.cssSelector("b")).getText().equals(product_name)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		// ng-animating
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		
		// Added cart item checking:-
		List<WebElement> cartproducts = driver.findElements(By.xpath("//*[@class='cartSection']/h3"));
		Boolean match = cartproducts.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(product_name));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")),"india").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		String conformation_msg = driver.findElement(By.cssSelector(".hero-primary")).getText();
		
		Assert.assertTrue(conformation_msg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
		
	}

}

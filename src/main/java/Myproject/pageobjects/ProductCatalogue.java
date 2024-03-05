package Myproject.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Myproject.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{

	WebDriver driver;

	// creating constructor
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	//List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
	// page factory
	@FindBy(css = ".mb-3")
	List<WebElement> products;
	
	@FindBy(css = ".ng-animating")
	WebElement spinner;
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	
	public List<WebElement> getproductlist() {
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String product_name)
	{
		WebElement prod = getproductlist().stream().
				filter(product -> product.findElement(By.cssSelector("b")).getText().equals(product_name)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String product_name) throws InterruptedException
	{
		WebElement prod = getProductByName(product_name);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementDisappear(spinner);
	}



	

}

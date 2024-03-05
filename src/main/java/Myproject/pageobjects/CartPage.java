package Myproject.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Myproject.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{

	WebDriver driver;
	@FindBy(css = ".totalRow button")
	WebElement checkoutele;
	
	@FindBy(css = ".cartSection h3")
	private List<WebElement> cartproducts;
	

	// creating constructor
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	
	public Boolean VerifyProductDisplay(String product_name) {
		Boolean match = cartproducts.stream()
				.anyMatch(cartproduct -> cartproduct.getText().equalsIgnoreCase(product_name));
		return match;
	}
	
	public checkOutPage goToCheckOut()
	{
		checkoutele.click();
		
		return new checkOutPage(driver);
	}
	
	


	

}

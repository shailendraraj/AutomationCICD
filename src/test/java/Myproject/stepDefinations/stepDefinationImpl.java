package Myproject.stepDefinations;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;

import Myproject.Testcomponents.BaseTest;
import Myproject.pageobjects.CartPage;
import Myproject.pageobjects.Landingpage;
import Myproject.pageobjects.ProductCatalogue;
import Myproject.pageobjects.checkOutPage;
import Myproject.pageobjects.conformationPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefinationImpl extends BaseTest{
	public Landingpage landingpage;
	public ProductCatalogue productCatalogue;
	public conformationPage conformationPage;
	@Given("I landed on Ecommerce Page")
	
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		landingpage = launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	
	public void Logged_in_with_username_and_password(String name, String password)
	{
		 productCatalogue = LandingPage.loginApplication(name,password);
	}
	
	@When("^I add product (.+) to Cart$")
	public void I_add_product_to_Cart(String productName) throws InterruptedException
	{
		List<WebElement> products = productCatalogue.getproductlist();
		productCatalogue.addProductToCart(productName);
	}
	
	@When("^Checkout (.+) and Submit the order$")
	public void Checkout_and_submit_the_order(String productName)
	{
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		checkOutPage checkOutPage = cartPage.goToCheckOut();
		checkOutPage.selectcountry("India");
		conformationPage = checkOutPage.submitOrder();
	}
	
	@Then("{string} Message is displayed on ConfirmationPage")
	public void Message_displayed_ConfirmationPage(String string)
	{
		String conformation_msg = conformationPage.getConformationMessage();
		Assert.assertTrue(conformation_msg.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("^\"([^\"]*)\" message is displayed$")
	public void message_displayed(String strArg1) throws InterruptedException
	{
		Assert.assertEquals(strArg1, LandingPage.getErrorMessage());
		driver.close();
	}
	

}

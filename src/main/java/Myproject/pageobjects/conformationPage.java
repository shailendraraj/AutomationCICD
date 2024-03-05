package Myproject.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Myproject.AbstractComponents.AbstractComponent;

public class conformationPage extends AbstractComponent{
	WebDriver driver;

	public conformationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css=".hero-primary")
	WebElement ConformationMessage;
	
	public String getConformationMessage()
	{
		return ConformationMessage.getText();
	}

}

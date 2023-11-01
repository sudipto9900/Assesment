package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	public WebDriver ldriver;
	
	public HomePage(WebDriver ldriver) {
		this.ldriver=ldriver;
		PageFactory.initElements(ldriver,this);
	}
	
	@FindBy(xpath="//li[@data-id='users']")
	WebElement listUsers;
	
	@FindBy(xpath="//li[@data-id='users-single']")
	WebElement singleUser;
	
	@FindBy(xpath = "//li[@data-id='users-single-not-found']")
	WebElement singleUserNotFoundButton;
	
	@FindBy(id = "supportOneTime")
	WebElement oneTime;
	
	@FindBy(id = "supportRecurring")
	WebElement supportRecurring;
	
	@FindBy(xpath="//button[text()='Support ReqRes']")
	WebElement supportButton;

	public void clicklistUsersButton() {
		listUsers.click();
	}
	
	public void clicksingleUserButton() {
		singleUser.click();
	}
	
	public void clickoneTime() {
		oneTime.click();
    }
	
	public void clicksupportButton() {
		supportButton.click();
    }
	
	public void clicksupportRecurring() {
		supportRecurring.click();
    }
	
	public WebElement getListUsers() {
        return listUsers;
    }

	public WebElement getSingleUser() {
        return singleUser;
    }

    public WebElement getSingleUserNotFoundButton() {
        return singleUserNotFoundButton;
    }
    
    public boolean hasOneTimeSupportOption() {
        return oneTime.isDisplayed();
    }
    
    public boolean hasMonthlySupportOption() {
        return supportRecurring.isDisplayed();
    }

    public boolean hasUpgradeDetails() {
        return supportButton.isDisplayed();
    }
}

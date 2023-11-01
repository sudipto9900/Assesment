package ResponsePage;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.cucumber.java.After;

public class ResponsePage {
	
    private WebDriver driver;
	 public ResponsePage(WebDriver driver) {
	        this.driver = driver;	  
	        PageFactory.initElements(driver, this);
	    }
	
	@FindBy(xpath="//*[@data-key='response-code']")
    WebElement responseCode;

    public void verifySuccessfulResponse() {
    	Assert.assertEquals(responseCode.getText(),"200");
    	
    }
    
    public void verifyUnSuccessfulResponse() {
    	Assert.assertEquals(responseCode.getText(),"404");
        }
    
    @After
    public void cleanup() {
        // Reset the responseCode WebElement after each test scenario
        responseCode = null;
    }
}

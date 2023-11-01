package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import pageObjects.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.hamcrest.Matchers.is;
import io.restassured.path.json.JsonPath;

import org.junit.Assert;

import static org.hamcrest.Matchers.equalTo;
import org.openqa.selenium.JavascriptExecutor;
import static org.hamcrest.Matchers.containsString;

import ResponsePage.ResponsePage;


public class Steps {
	public WebDriver driver;
	public HomePage sp;
	public ResponsePage rsp;
	public Response lastResponse;
	
	@Given("the user is on the home page of the application")
	public void the_user_is_on_the_home_page_of_the_application() {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\sudip\\eclipse-workspace\\Cucumber_BDD_Example\\Drivers\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
	    options.addArguments("--remote-allow-origins=*");
		driver=new ChromeDriver(options);
	    sp=new HomePage(driver);
	    driver.get("https://reqres.in/");   
	    driver.manage().window().maximize();
	}

	@When("the user selects {string}")
	public void the_user_selects(String button) {
		WebElement buttonElement = null;
		switch (button) {
        case "LIST USERS":
        	buttonElement = sp.getListUsers();
            break;
        case "SINGLE USER":
        	buttonElement = sp.getSingleUser();
            break;
        case "SINGLE USER NOT FOUND":
        	buttonElement = sp.getSingleUserNotFoundButton();
            break;
    }
		if (buttonElement != null) {
            // Scroll to the element to make it visible
            scrollIntoView(buttonElement);

            // Click the button
            buttonElement.click();
        } else {
            throw new IllegalArgumentException("Button not found: " + button);
        }
	   
	}
	
	private void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

	@Then("user should see a successful response with detailed validation")
	public void user_should_see_a_successful_response_with_detailed_validation() {
		lastResponse = RestAssured.get("https://reqres.in/api/users?page=2");
		lastResponse.then().statusCode(200);
        lastResponse.then().assertThat().contentType("application/json");
        lastResponse.then().body("data[0].id", is(7));
        lastResponse.then().body("data[0].email", equalTo("michael.lawson@reqres.in"));
      
	}

	@Then("User should see a successful response")
	public void user_should_see_a_successful_response() {
		rsp=new ResponsePage(driver);
		rsp.verifySuccessfulResponse();
	}
	
	@When("User should not see a successful response")
	public void user_should_not_see_a_successful_response() {
		lastResponse = RestAssured.get("https://reqres.in/api/users/23");
		lastResponse.then().statusCode(404);
		rsp=new ResponsePage(driver);
		rsp.verifyUnSuccessfulResponse();
	}
	
	@Given("Homepage should display options for one-time and monthly support")
	public void homepage_should_display_options_for_one_time_and_monthly_support() {
		sp=new HomePage(driver);
		sp.hasOneTimeSupportOption();
		sp.hasMonthlySupportOption();
	}
	
	@When("the user clicks on the Support link after selecting one time or monthly payment")
	public void the_user_clicks_on_the_support_link_after_selecting_one_time_or_monthly_payment() {
		sp.clickoneTime();
	    sp.clicksupportButton();
	}
	
	@Then("the detail payment for upgrade details should be displayed")
	public void the_detail_payment_for_upgrade_details_should_be_displayed() {
		String currentUrl = driver.getCurrentUrl();
        if (currentUrl.contains("https://checkout.stripe.com/")) {
            System.out.println("The correct page has loaded.");
        } else {
            System.out.println("The page did not load as expected.");
        }
	}
	
	@When("the user sends a POST request to {string} with body:")
    public void the_user_sends_a_POST_request_to_with_body(String url, String requestBody) {
        lastResponse = RestAssured.given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post(url);
    }
	
	@Then("the response status code should be {int}")
    public void the_response_status_code_should_be(Integer expectedStatusCode) {
        lastResponse.then().statusCode(expectedStatusCode);
    }
	
	@Then("the response body should contain:")
    public void the_response_body_should_contain(String expectedResponseBody) {
		Response response = lastResponse;
		JsonPath jsonPath = new JsonPath(response.getBody().asString());
	    JsonPath expectedJsonPath = new JsonPath(expectedResponseBody);
	    Assert.assertEquals(expectedJsonPath.getString("name"), jsonPath.getString("name"));
	    Assert.assertEquals(expectedJsonPath.getString("job"), jsonPath.getString("job"));
    }

	
	@After
    public void closeBrowser() {
        driver.quit();
    }
	
	

}

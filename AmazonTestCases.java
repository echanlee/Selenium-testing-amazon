import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

class AmazonTestCases {

	private String url = "https://www.amazon.ca/";
	//For my testing browser this was the text shown but in local browser there was different text
	private String loginText = "Hello, Sign in\nAccount & Lists"; 
	private String email = "evette.emcl@gmail.com";
	private String password = "password";
	private String signInUrl = "https://www.amazon.ca/?ref_=nav_custrec_signin&";
	private String validSearch = "memory card";
	private String invalidSearch = "[alpja]";
	private String noResultsSearch = "No results for";
	private WebDriver browser;

	@BeforeEach
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\evett\\chromedriver.exe");
		browser = new ChromeDriver();
		browser.get(url);
	}

	@Test
	public void loginText() {
		// navigates to amazon and checks text
		browser.getTitle();
		WebElement login = browser.findElement(By.xpath("//*[@id=\"nav-link-accountList\"]"));
		assertEquals(loginText, login.getText());
	}

	@Test
	public void login() {
		// navigates to amazon and checks text and logs in
		browser.getTitle();
		WebElement login = browser.findElement(By.xpath("//*[@id=\"nav-signin-tooltip\"]/a/span"));
		login.click();
		browser.findElement(By.xpath("//*[@id=\"ap_email\"]")).sendKeys(email);
		browser.findElement(By.xpath("//*[@id=\"ap_password\"]")).sendKeys(password);
		browser.findElement(By.xpath("//*[@id=\"signInSubmit\"]")).click();
		assertEquals(signInUrl, browser.getCurrentUrl());
	}

	@Test
	public void validSearch() {
		browser.findElement(By.xpath("//*[@id=\"twotabsearchtextbox\"]")).sendKeys(validSearch);
		browser.findElement(By.xpath("//*[@id=\"nav-search\"]/form/div[2]/div/input")).click();
		WebElement searchResults = browser
				.findElement(By.xpath("//*[@id=\"search\"]/span/h1/div/div[1]/div/div/span[1]"));
		assertNotNull(searchResults.getText());
	}

	@Test
	public void invalidSearch() {
		browser.findElement(By.xpath("//*[@id=\"twotabsearchtextbox\"]")).sendKeys(invalidSearch);
		browser.findElement(By.xpath("//*[@id=\"nav-search\"]/form/div[2]/div/input")).click();
		WebElement searchResults = browser
				.findElement(By.xpath("//*[@id=\"search\"]/div[1]/div[2]/div/span[2]/div/div[1]/span[1]"));
		assertEquals(noResultsSearch, searchResults.getText());
	}
}

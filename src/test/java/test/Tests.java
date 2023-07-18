package test;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import page.Sitepage;

public class Tests {
	ChromeDriver cd;

	@BeforeTest
	public void beft()
	{
		cd=new ChromeDriver();
		cd.manage().window().maximize();
	}
	@BeforeMethod
	public void befm()
	{
		cd.get("https://petstore.swagger.io/");
	}
	@Test
	public void test1() throws IOException
	{
		Sitepage st=new Sitepage(cd);
		WebDriverWait wb=new WebDriverWait(cd, Duration.ofSeconds(30));
		wb.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"swagger-ui\"]/section/div[2]/div[2]/div[2]/section/label")));
		st.dropdown();
		st.title();
		st.tryit();
		st.search();
		st.screenshot();
		st.newtab();
		st.respcode();
		
	}
}

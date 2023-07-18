package page;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;

public class Sitepage {

	ChromeDriver cd;
	
	By dropdown=By.xpath("//*[@id=\"swagger-ui\"]/section/div[2]/div[2]/div[2]/section/label");
	By https1=By.xpath("//*[@id=\"swagger-ui\"]/section/div[2]/div[2]/div[2]/section/label/select/option[2]");
	By title=By.xpath("//*[@id=\"swagger-ui\"]/section/div[2]/div[2]/div[1]/section/div/div/hgroup/h2");
	By post=By.xpath("//*[@id=\"operations-pet-uploadFile\"]/div/button[1]/span[1]");
	By tryitout=By.xpath("//*[@id=\"operations-pet-uploadFile\"]/div[2]/div/div[1]/div[1]/div[2]/button");
	By PetID=By.xpath("//*[@id=\"operations-pet-uploadFile\"]/div[2]/div/div[1]/div[2]/div/table/tbody/tr[1]/td[2]/input");
	By addtnldata=By.xpath("//*[@id=\"operations-pet-uploadFile\"]/div[2]/div/div[1]/div[2]/div/table/tbody/tr[2]/td[2]/input");
	By searchbar=By.xpath("//*[@id=\"swagger-ui\"]/section/div[1]/div/div/form/input");
	
	public Sitepage(ChromeDriver cd)
	{
		this.cd=cd;
	}
	
	public void dropdown()
	{
		cd.findElement(dropdown).click();
		cd.findElement(https1).click();
		//Select s=new Select(cd.findElement(dropdown));
		//s.selectByVisibleText("HTTP");
		
	}
	public void title()
	{
		WebElement s=cd.findElement(title);
		String d=s.getText();
		System.out.println(d);	
		}
	public void tryit()
	{
		cd.findElement(post).click();
		cd.findElement(tryitout).click();
		cd.findElement(PetID).sendKeys("LAB");
		cd.findElement(addtnldata).sendKeys("Small");
	}
	public void search()
	{
		cd.findElement(searchbar).click();
		Actions a =new Actions(cd);
		WebElement w=cd.findElement(searchbar);
		a.keyDown(w,Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL);
		a.keyDown(Keys.DELETE);
}
	
	public void screenshot() throws IOException
	{
		File s=((TakesScreenshot)cd).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(s, new File("C:\\Users\\ASUS\\Desktop\\Software Testing\\picture.png"));
		
		WebElement we=cd.findElement(title);
		File sc=(we.getScreenshotAs(OutputType.FILE));
		FileHandler.copy(sc, new File("C:\\Users\\ASUS\\Desktop\\Software Testing\\picture1.png"));
	}
	
	public void newtab()
	{
		String pwindow=cd.getWindowHandle();
		
		WebDriver wb=cd.switchTo().newWindow(WindowType.TAB);
		cd.get("https://www.google.com/");
		Set<String>window=cd.getWindowHandles();
		for(String allwindow:window)
		{
			if(!allwindow.equalsIgnoreCase(pwindow))
			{
				cd.switchTo().window(pwindow);
			}
		}
	}
	
	public void respcode() throws IOException
	{
		List<WebElement>link=cd.findElements(By.tagName("a"));
		System.out.println("The count of links are"+link.size());
		for(WebElement links:link)
		{
			String l=links.getAttribute("href");
		}
		String crnturl=cd.getCurrentUrl();
		URL u=new URL(crnturl);
		HttpURLConnection ur=(HttpURLConnection)u.openConnection();
		ur.connect();
		
		if(ur.getResponseCode()==200)
		{
			System.out.println("The response code is 200");
		}
		else
		if(ur.getResponseCode()==400)
		{
			System.out.println("The response code is 400");
		}
	}
}
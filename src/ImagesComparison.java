import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert; 
import org.testng.annotations.Test;   

public class ImagaesComparison {
	
	WebDriverWait wait;
	
	
	public void waitVisibility(By elementBy) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(elementBy));
			
	}
	
	
	public static void main(String args[]) throws InterruptedException, IOException {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Automation\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		WebDriverWait wait;
		wait = new WebDriverWait(driver,15);
		
		driver.get("http://demo.automationtesting.in/Register.html");
		WebElement webElement = driver.findElement(By.cssSelector("#imagetrgt"));
		wait.until(ExpectedConditions.visibilityOf(webElement));
		
		
		Screenshot screenshot = new AShot().takeScreenshot(driver, webElement);
			
		ImageIO.write(screenshot.getImage(), "PNG", new File(System.getProperty("user.dir")+ "\\test-output\\Images\\ElementScreenshott.png"));

		System.out.println(ImageIO.write(screenshot.getImage(), "PNG", new File(System.getProperty("user.dir")+ "\\test-output\\Images\\ElementScreenshot.png")));
		System.out.println(System.getProperty("user.dir")+ "\\test-output\\Images\\ElementScreenshot.png");
			
		Thread.sleep(2000);
		driver.quit();
		
	}
	
	public class ImageComparisonPositive{
		
		WebDriver driver;
		WebDriverWait wait;
		
		@Test
		public void ImageCompPositive() throws InterruptedException, IOException {
		
			
			System.setProperty("webdriver.chrome.driver", "C:\\Automation\\chromedriver.exe");
			driver = new ChromeDriver();
			wait = new WebDriverWait(driver,15);
			
			driver.get("http://demo.automationtesting.in/Register.html");
	
			WebElement logoImage = driver.findElement(By.cssSelector("#basicBootstrapForm > div:nth-child(1) > div:nth-child(2) > input"));
			wait.until(ExpectedConditions.visibilityOf(logoImage));
			
			
			BufferedImage expectedImage = ImageIO.read(new File(System.getProperty("user.dir")+ "\\test-output\\Images\\ElementScreenshot.png"));
			Screenshot logoImageScreenshot = new AShot().takeScreenshot(driver, logoImage);
			BufferedImage actualImage = logoImageScreenshot.getImage();
			
			ImageDiffer imgDiff = new ImageDiffer();
			ImageDiff diff = imgDiff.makeDiff(actualImage, expectedImage);
			Assert.assertTrue(diff.hasDiff(),"Images are the same");
			
			driver.quit();
		}
		
}
}


	
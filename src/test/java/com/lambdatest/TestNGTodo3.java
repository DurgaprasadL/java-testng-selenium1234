package com.lambdatest;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import java.time.Duration;
import java.io.File;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Parameters;

public class TestNGTodo3 {

    private RemoteWebDriver driver;
    private String Status = "failed";
    

    @Parameters({"browser","version","operatingSystem"})
    @BeforeMethod()
    public void setup(Method m, ITestContext ctx, String browser,String version,String operatingSystem) throws MalformedURLException, InterruptedException {
        String username = System.getenv("LT_USERNAME") == null ? "Your LT Username" : System.getenv("LT_USERNAME");
        String authkey = System.getenv("LT_ACCESS_KEY") == null ? "Your LT AccessKey" : System.getenv("LT_ACCESS_KEY");
        
        String hub = "@hub.lambdatest.com/wd/hub";

        
        DesiredCapabilities caps = new DesiredCapabilities();
        //caps.setCapability("tunnel", true);
        caps.setCapability("platform", "macOS Sierra");
        caps.setCapability("browserName", "microsoftedge");
        caps.setCapability("version", "87.0");
        // caps.setCapability("platform", operatingSystem);
        // caps.setCapability("browserName", browser);
        // caps.setCapability("version", version);
        caps.setCapability("build", "TestNG With Java");
        caps.setCapability("name", m.getName() + this.getClass().getName());
        caps.setCapability("plugin", "git-testng");


        String[] Tags = new String[] { "Feature", "Falcon", "Severe" };
        caps.setCapability("tags", Tags);

        driver = new RemoteWebDriver(new URL("https://" + username + ":" + authkey + hub), caps);

        EdgeOptions browserOptions = new EdgeOptions();
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("username", "durgaprasadlokavarapu1");
        ltOptions.put("accessKey", "L5diX2dR31OmsPDWOQmkvSDh43EYc9gPeXR5N8ZAZBAv9svwVt");
        ltOptions.put("visual", true);
        ltOptions.put("video", true);
        ltOptions.put("project", "Cert");
        ltOptions.put("name", "Test1");
        ltOptions.put("tunnel", true);
        ltOptions.put("console", "info");
        ltOptions.put("selenium_version", "4.0.0");
        ltOptions.put("w3c", true);
        ltOptions.put("plugin", "java-testNG");
        browserOptions.setCapability("LT:Options", ltOptions);


        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS );
        Thread.sleep(5000);

       driver.get("https://www.lambdatest.com/.");
       Thread.sleep(5000);



    }

    @Test
    public void basicTest() throws InterruptedException {
       
		//Get url
		String url = driver.getCurrentUrl();
		
		//Scroll to See All Integrations
		WebElement seeAllIntegrations = driver.findElement(By.xpath("//a[text()='See All Integrations']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", seeAllIntegrations);
		
        //Open link 
		js.executeScript("arguments[0].click();", seeAllIntegrations);
		Thread.sleep(10000);
		
         //Print the Window handles
		  Set<String> windowHandles = driver.getWindowHandles();
		  List<String> windowStrings = new ArrayList<>(windowHandles);
		  System.out.println(windowStrings);
		  int count = windowStrings.size();
		  System.out.println(count);
		  
		    //Scroll to codeless Automation
		    WebElement codelessAutomation = driver.findElement(By.xpath("//a[text()='Codeless Automation']"));
		    js.executeScript("arguments[0].scrollIntoView();", codelessAutomation);
		    
		    //Click on learn more link of Testing Whiz
		     WebElement testingWhiz = driver.findElement(By.xpath("//a[text()='Integrate Testing Whiz with LambdaTest']"));
		    Actions ac = new Actions(driver);
		    ac.moveToElement(testingWhiz);
		    testingWhiz.click();
		    Thread.sleep(10000);
		    
		    //Verifying page title
		    String text = driver.findElement(By.xpath("//h1[contains(text(),'TestingWhiz Integration')]")).getText();
		    Assert.assertTrue(text.equals("TestingWhiz Integration With LambdaTest"));
		
		    //on current window set url
		    driver.get("https://www.lambdatest.com/blog.");
		
		    //Click on Community link
		    driver.findElement(By.xpath("(//a[text()='Community'])[1]")).click();
	        Thread.sleep(10000);
		
		     //Verifying url
		     Assert.assertTrue(driver.getCurrentUrl().equals("https://community.lambdatest.com/"));
		
		    //Close current window
             driver.close();

             Status = "passed";
             Thread.sleep(150);

             System.out.println("TestFinished");
        
         
	}

//@AfterMethod
    public void tearDown() {
        driver.executeScript("lambda-status=" + Status);
        driver.quit();
    }
}
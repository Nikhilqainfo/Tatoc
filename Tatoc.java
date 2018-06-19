package newpackage;
import java.util.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.By.ByLinkText;

import org.openqa.selenium.interactions.Action;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class Tatoc {
	public static void main(String[] args)
	{
	WebDriver chrome=new ChromeDriver();
	chrome.get("http://10.0.1.86/tatoc");
	
	///FOR GREEN COLOUR BOX
	List<WebElement> myelements =chrome.findElements(By.cssSelector("a"));
	myelements.get(0).click();
	chrome.findElement(By.className("greenbox")).click();
	
	// FOR MATCHING COLOUR
	chrome.switchTo().frame("main");
	WebElement colourclass1=chrome.findElement(By.id("answer")); 
	String resultcolour = colourclass1.getAttribute("class");
	
	int flag=1;
	String comparecolour;
	
	while(flag>0)
		{
		chrome.switchTo().frame("child");
		WebElement colourclass2=chrome.findElement(By.id("answer"));
		comparecolour=colourclass2.getAttribute("class");
		
		
		if(comparecolour.equals(resultcolour))
		{
			chrome.switchTo().defaultContent();
			chrome.switchTo().frame("main");
			
			flag=0;
			chrome.findElement(By.linkText("Proceed")).click();
			break;
		}
		else {
			chrome.switchTo().defaultContent();
			chrome.switchTo().frame("main");
			chrome.findElement(By.linkText("Repaint Box 2")).click();
		} 
		
		}
	
	//FOR DRAG AND DROP BOX	
	 WebElement dragbox = chrome.findElement(By.id("dragbox"));
	 WebElement dropbox = chrome.findElement(By.id("dropbox"));

	 Actions action = new Actions(chrome);
	 Action dragAndDrop = action.clickAndHold(dragbox).moveToElement(dropbox).release(dragbox).build();
	 dragAndDrop.perform();
	 chrome.findElement(By.linkText("Proceed")).click();
	 
	 // FOR 
	 List<WebElement> myele =chrome.findElements(By.cssSelector("a"));
	 myele.get(0).click();
	 
	  String MainWindow=chrome.getWindowHandle();		//main window object
	  Set<String> s1=chrome.getWindowHandles(); //list of strings		
      Iterator<String> i1=s1.iterator();		
      		
      while(i1.hasNext())			
      {		
          String ChildWindow=i1.next();		
          		
          if(!MainWindow.equalsIgnoreCase(ChildWindow))			
          {    		
                  chrome.switchTo().window(ChildWindow);	                                                                                                           
                  chrome.findElement(By.id("name")).sendKeys("Nikhil");                			
                  chrome.findElement(By.id("submit")).click();			
                  		
          }		
      }		
      chrome.switchTo().window(MainWindow);
      myele.get(1).click();
	 
      List<WebElement> tokenanchors =chrome.findElements(By.cssSelector("a"));
      tokenanchors.get(0).click();
      String Token=chrome.findElement(By.id("token")).getText().substring(7);
	    System.out.println(Token);
	    Cookie ck = new Cookie("Token", Token);
	    chrome.manage().addCookie(ck);
	    tokenanchors.get(1).click();
    
	 //chrome.findElement(By.id("name")).sendKeys("cxzczczx");
	 //chrome.findElement(By.id("submit")).submit();
	
	
	}
}

package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class NavigationHelper extends HelperBase{

  public NavigationHelper(ChromeDriver wd) {
   super(wd);
  }

  public void goToGroupPage() {
    if (isElementPresent(By.tagName("h1"))
            && wd.findElement(By.tagName("h1")).getText().equals("Groups")
            && isElementPresent(By.name("new"))) {
      return;
    } else {
      click(By.linkText("groups"));
    }
  }

  public void goToAddNewPage(){
    if (isElementPresent(By.name("update"))){
      return;
    } else {
      click(By.linkText("add new"));
    }
  }

  public void goToHomePage() {
    if (isElementPresent(By.id("maintable"))) {
      return;
    } else{
      click(By.linkText("home"));
    }
  }

}

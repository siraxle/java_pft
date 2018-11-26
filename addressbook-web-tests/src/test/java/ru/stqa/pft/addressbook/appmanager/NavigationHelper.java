package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class NavigationHelper extends HelperBase{

  public NavigationHelper(ChromeDriver wd) {
   super(wd);
  }

  public void goToGroupPage() {
    click(By.linkText("groups"));
  }

  public void goToEditAddPage(){
    click(By.linkText("add new"));
  }

}

package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stqa.pft.addressbook.model.AddNewData;

public class AddNewHelper extends HelperBase{

  public AddNewHelper(ChromeDriver wd) {
    super(wd);
  }

  public void clickEnterOnAddNewPage() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillAddNew(AddNewData addNewData) {
    wd.findElement(By.name("firstname")).click();
    wd.findElement(By.name("firstname")).clear();
    wd.findElement(By.name("firstname")).sendKeys(addNewData.getFirstName());
    wd.findElement(By.name("middlename")).click();
    wd.findElement(By.name("middlename")).clear();
    wd.findElement(By.name("middlename")).sendKeys(addNewData.getMiddleName());
    wd.findElement(By.name("lastname")).click();
    wd.findElement(By.name("lastname")).clear();
    wd.findElement(By.name("lastname")).sendKeys(addNewData.getLastName());
    wd.findElement(By.name("nickname")).click();
    wd.findElement(By.name("nickname")).clear();
    wd.findElement(By.name("nickname")).sendKeys(addNewData.getNickName());
    wd.findElement(By.name("title")).click();
    wd.findElement(By.name("mobile")).click();
    wd.findElement(By.name("mobile")).clear();
    wd.findElement(By.name("mobile")).sendKeys(addNewData.getMobile());
    wd.findElement(By.name("email")).click();
    wd.findElement(By.name("email")).clear();
    wd.findElement(By.name("email")).sendKeys(addNewData.getEmail());
    if (!wd.findElement(By.xpath("//div[@id='content']/form/select[1]//option[10]")).isSelected()) {
      wd.findElement(By.xpath("//div[@id='content']/form/select[1]//option[10]")).click();
    }
    if (!wd.findElement(By.xpath("//div[@id='content']/form/select[2]//option[12]")).isSelected()) {
      wd.findElement(By.xpath("//div[@id='content']/form/select[2]//option[12]")).click();
    }
    wd.findElement(By.name("byear")).click();
    wd.findElement(By.name("byear")).clear();
    wd.findElement(By.name("byear")).sendKeys(addNewData.getByear());
    wd.findElement(By.name("theform")).click();
    wd.findElement(By.name("notes")).click();
    wd.findElement(By.name("notes")).clear();
    wd.findElement(By.name("notes")).sendKeys(addNewData.getNotes());
  }
}

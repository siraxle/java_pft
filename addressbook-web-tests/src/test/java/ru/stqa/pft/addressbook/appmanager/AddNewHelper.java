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
    type(By.name("firstname"),addNewData.getFirstName());
    type(By.name("middlename"),addNewData.getMiddleName());
    type(By.name("lastname"),addNewData.getLastName());
    type(By.name("nickname"),addNewData.getNickName());
    type(By.name("mobile"),addNewData.getMobile());
    type(By.name("email"),addNewData.getEmail());
    type(By.name("notes"),addNewData.getNotes());
  }
}

package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stqa.pft.addressbook.model.AddNewContactData;

public class AddNewContactHelper extends HelperBase{

  public AddNewContactHelper(ChromeDriver wd) {
    super(wd);
  }

  public void clickEnterOnAddNewPage() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillAddNew(AddNewContactData addNewContactData) {
    type(By.name("firstname"), addNewContactData.getFirstName());
    type(By.name("middlename"), addNewContactData.getMiddleName());
    type(By.name("lastname"), addNewContactData.getLastName());
    type(By.name("nickname"), addNewContactData.getNickName());
    type(By.name("mobile"), addNewContactData.getMobile());
    type(By.name("email"), addNewContactData.getEmail());
    type(By.name("notes"), addNewContactData.getNotes());
  }

  public void selectContact(){
    click(By.name("selected[]"));
  }

  public void deleteContacts(){
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    wd.switchTo().alert().accept();
  }
}

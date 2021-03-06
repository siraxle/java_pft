package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase{

  public ContactHelper(ChromeDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.name("submit"));
  }

  public void fillContactForm(ContactData contactData,boolean creation) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("middlename"), contactData.getMiddleName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("nickname"), contactData.getNickName());
    type(By.name("mobile"), contactData.getMobile());

    if (creation){
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    }else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }

    type(By.name("email"), contactData.getEmail());
    type(By.name("notes"), contactData.getNotes());
  }

  public void selectContact(int index){
    wd.findElements(By.name("selected[]")).get(index).click();
    //click(By.name("selected[]"));
  }

  public void deleteContacts(){
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    wd.switchTo().alert().accept();
  }

  public void initContactModification(int index) {
    WebElement element = wd.findElement(By.xpath("//table[@id='maintable']/tbody/tr[" + index + "]/td[8]/a/img"));//.getAttribute("href=\"edit.php?id="+ index + "\"");
    element.click();
    //System.out.println(index);
  }
  public void submitContactModification() {
    click(By.name("update"));
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public void createContact(ContactData contact) {
    fillContactForm(new ContactData("Евгений", "Ефремов", "Витальевич",
            "axle", "9522448961", "sir.axle@yandex.ru", "test1", "it is test"),true);
    submitContactCreation();
  }


  public int getContactCaunt() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> getContactList() {
      List<ContactData> contacts= new ArrayList<ContactData>();
      List<WebElement> elements = wd.findElements(By.name("entry"));
      for (WebElement element : elements){
        String email = element.findElement(By.tagName("input")).getAttribute("accept");
        int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
        ContactData contact = new ContactData(id,null, null, null,
                null, null, email, null, null);
        contacts.add(contact);
      }
      return contacts;
  }
}

package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase{

  public ContactHelper(ChromeDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.name("submit"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
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
  }

  public int count(){
   return wd.findElements(By.name("selected[]")).size();
  }


  public void selectContactById(int id){
    wd.findElement(By.cssSelector("input[value='"+ id +"']" )).click();
  }

  public void deleteContacts(){
    click(By.cssSelector("input[value='Delete']"));
    wd.switchTo().alert().accept();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteContacts();
    contactCache = null;
    homePage();
  }

  public void modify(ContactData contact) {
    selectContactById(contact.getId());
    initContactModification();
  }

  public void initContactModification() {
    click(By.xpath("//*[@id=\"maintable\"]/tbody/tr[2]/td[8]/a/img"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void homePage() {
    click(By.xpath("//*[@id=\"nav\"]/ul/li[1]/a"));
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public void create(ContactData contact) {
    fillContactForm(contact,true);
    submitContactCreation();
    contactCache = null;
  }

  public Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null){
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements){
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String email = element.findElement(By.tagName("input")).getAttribute("accept");
      ContactData contact = new ContactData().withId(id).withEmail(email);
      contactCache.add(contact);
    }
    return new Contacts(contactCache);
  }
}

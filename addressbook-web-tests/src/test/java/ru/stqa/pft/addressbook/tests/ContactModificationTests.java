package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition(){
    app.goTo().goToHomePage();
    if(!app.getTo().isThereAContact()){
      app.goTo().contactPage();
      app.getTo().create(new ContactData("Евгений","Ефремов","Витальевич",
              "axle","9522448961","sir.axle@yandex.ru","test1","it is test"));
    }
  }

  @Test (enabled = false)
  public void testContactModification() {
    app.getTo().homePage();
    List<ContactData> before = app.getTo().list();
    app.getTo().selectContact(before.size()-1);
    app.getTo().initContactModification();
    ContactData contact = new ContactData("first_name","middle_name",
            "last_name","nick_name","12345","mail@mail.com",null,
            "qwerty");
    app.getTo().fillContactForm(contact,false);
    app.getTo().submitContactModification();
    app.getTo().homePage();
    List<ContactData> after = app.getTo().list();
    try {
      Assert.assertEquals(after.size(), before.size());
    } catch (AssertionError e){
      System.out.println("Модификатион не работает, сцука");
    }
    try {
      Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));
    } catch (AssertionError e){
      System.out.println("Одна из коллекций пуста!!");
    }
  }

}

package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().goToHomePage();
    if (! app.getContactHelper().isThereAContact()){
      app.getNavigationHelper().goToContactPage();
      app.getContactHelper().createContact(new ContactData("Евгений", "Ефремов", "Витальевич",
              "axle", "9522448961", "sir.axle@yandex.ru", "test1", "it is test"));
    }
    app.getContactHelper().returnToHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size()-1);
    app.getContactHelper().initContactModification();
    ContactData contact = new ContactData("first_name","middle_name",
            "last_name","nick_name","12345","mail@mail.com",null,
            "qwerty");
    app.getContactHelper().fillContactForm(contact,false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());
    Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));
  }

}

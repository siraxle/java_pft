package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test (enabled = false)
  public void testContactModification() {
    app.goTo().goToHomePage();
    if (! app.getContactHelper().isThereAContact()){
      app.goTo().goToAddNewPage();
      app.getContactHelper().createContact(new ContactData("Евгений", "Ефремов", "Витальевич",
              "axle", "9522448961", "sir.axle@yandex.ru", "test1", "it is test"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initContactModification(before.size() + 1);
    ContactData contact = new ContactData(before.get(before.size()-1).getId(),"first_name","middle_name",
            "last_name","nick_name","12345","mail@mail.com",null,
            "qwerty");
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
//    app.goTo().goToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(),before.size());
    //before.remove(before.size()-1);
    //before.add(contact);
    Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));
  }

}

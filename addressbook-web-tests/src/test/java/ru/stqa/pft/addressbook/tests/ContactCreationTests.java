package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation(){
    app.getContactHelper().returnToHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getNavigationHelper().goToContactPage();
    ContactData contact = new ContactData("Евгений", "Ефремов", "Витальевич",
            "axle", "9522448961", "sir.axle@yandex.ru","test1", "it is test");
    app.getContactHelper().createContact(contact);
    app.getContactHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(before.size() + 1, after.size());
    before.add(contact);
    int max = 0;
    for (ContactData c : after){
      if (c.getId() > max){
        max = c.getId();
      }
    }
    contact.setId(max);
    Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));
  }
}
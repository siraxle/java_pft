package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test (enabled = false)
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

    Comparator<? super ContactData> byId = (Comparator<ContactData>) (o1,o2) -> Integer.compare(o1.getId(), o2.getId());
    int max2 = after.stream().max(byId).get().getId();
    contact.setId(max2);
    Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));
  }
}
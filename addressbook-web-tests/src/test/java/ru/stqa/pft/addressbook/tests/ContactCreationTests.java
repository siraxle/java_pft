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
    app.getTo().homePage();
    List<ContactData> before = app.getTo().list();
    app.goTo().contactPage();
    ContactData contact = new ContactData("Евгений", "Ефремов", "Витальевич",
            "axle", "9522448961", "sir.axle@yandex.ru","test1", "it is test");
    app.getTo().create(contact);
    app.getTo().homePage();
    List<ContactData> after = app.getTo().list();
    Assert.assertEquals(before.size() + 1, after.size());
    before.add(contact);
    Comparator<? super ContactData> byId = (Comparator<ContactData>) (o1,o2) -> Integer.compare(o1.getId(), o2.getId());
    int max = after.stream().max(byId).get().getId();
    contact.setId(max);
    Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));
  }
}
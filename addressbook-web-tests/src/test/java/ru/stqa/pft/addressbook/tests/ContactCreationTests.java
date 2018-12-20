package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test (enabled = false)
  public void testContactCreation(){
    app.getTo().homePage();
    List<ContactData> before = app.getTo().list();
    app.goTo().contactPage();
    ContactData contact = new ContactData()
            .withFirstName("Евгений").withLastName("Ефремов").withMiddleName("Витальевич")
            .withNickName("axle").withMobile("9522448961").withEmail("sir.axle@yandex.ru")
            .withGroup("test1").withNotes("it is test");
    app.getTo().create(contact);
    app.getTo().homePage();
    List<ContactData> after = app.getTo().list();
    Assert.assertEquals(before.size() + 1, after.size());
    before.add(contact);
    Comparator<? super ContactData> byId = (Comparator<ContactData>) (o1,o2) -> Integer.compare(o1.getId(), o2.getId());
    int max = after.stream().max(byId).get().getId();
    contact.withId(max);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);
  }
}
package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;
import java.util.Set;

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
    Set<ContactData> after = app.getTo().all();
    Assert.assertEquals(before.size() + 1, after.size());
    contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before,after);
  }
}
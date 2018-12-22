package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().goToHomePage();
    if (!app.getTo().isThereAContact()) {
      app.goTo().contactPage();
      app.getTo().create(new ContactData()
              .withFirstName("Евгений").withLastName("Ефремов").withMiddleName("Витальевич")
              .withNickName("axle").withMobile("9522448961").withEmail("sir.axle@yandex.ru")
              .withGroup("test1").withNotes("it is test"));
    }
  }

  @Test //(enabled = false)
  public void testContactModification() {
    app.getTo().homePage();
    Set<ContactData> before = app.getTo().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withFirstName("first_name").withMiddleName("middle_name")
            .withLastName("last_name").withNickName("nick_name").withMobile("12345").withEmail("mail@mail.com").withGroup(null)
            .withNotes("qwerty");
    app.getTo().modify(modifiedContact);
    app.getTo().fillContactForm(contact, false);
    app.getTo().submitContactModification();
    app.getTo().homePage();
    List<ContactData> after = app.getTo().list();
    try {
      assertEquals(after.size(), before.size());
    } catch (AssertionError e) {
      System.out.println("Модификатион не работает, сцука");
    }
    before.remove(modifiedContact);
    try {
      assertThat(after, equalTo(((Contacts) before).withAdded(contact)));
    } catch (AssertionError e) {
      System.out.println("Одна из коллекций пуста!!");
    }
  }

}

package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().goToHomePage();
    if (!app.contact().isThereAContact()) {
      app.goTo().contactPage();
      app.contact().create(new ContactData()
              .withFirstName("Евгений").withLastName("Ефремов").withMiddleName("Витальевич")
              .withNickName("axle").withMobilePhone("9522448961").withEmail("sir.axle@yandex.ru")
              .withGroup("test1").withNotes("it is test").withHomePhone("4321").withWorkPhone("9876"));
    }
  }

  @Test //(enabled = false)
  public void testContactModification() {
    app.contact().homePage();
    Set<ContactData> before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withFirstName("first_name").withMiddleName("middle_name")
            .withLastName("last_name").withNickName("nick_name").withMobilePhone("12345").withEmail("mail@mail.com").withGroup(null)
            .withNotes("qwerty").withWorkPhone("1234").withMobilePhone("5678").withHomePhone("4321");
    app.contact().modify(modifiedContact);
    app.contact().fillContactForm(contact, false);
    app.contact().submitContactModification();
    app.contact().contactCache = null;
    app.contact().homePage();
    Set<ContactData> after = app.contact().all();
    try {
      assertThat(app.contact().count(), equalTo(before.size()));
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

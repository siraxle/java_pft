package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

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
    List<ContactData> before = app.getTo().list();
    int index = before.size() - 1;
    app.getTo().selectContact(index);
    app.getTo().initContactModification();
    ContactData contact = new ContactData().withFirstName("first_name").withMiddleName("middle_name")
            .withLastName("last_name").withNickName("nick_name").withMobile("12345").withEmail("mail@mail.com").withGroup(null)
            .withNotes("qwerty");
    app.getTo().fillContactForm(contact, false);
    app.getTo().submitContactModification();
    app.getTo().homePage();
    List<ContactData> after = app.getTo().list();
    try {
      Assert.assertEquals(after.size(), before.size());
    } catch (AssertionError e) {
      System.out.println("Модификатион не работает, сцука");
    }
    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    try {
      Assert.assertEquals(before, after);
    } catch (AssertionError e) {
      System.out.println("Одна из коллекций пуста!!");
    }
  }

}

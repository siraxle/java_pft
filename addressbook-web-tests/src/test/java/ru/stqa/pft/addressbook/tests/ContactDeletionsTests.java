package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionsTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().goToHomePage();
    if (!app.getTo().isThereAContact()) {
      app.goTo().contactPage();
      app.getTo().create(new ContactData("Евгений", "Ефремов", "Витальевич",
              "axle", "9522448961", "sir.axle@yandex.ru", "test1", "it is test"));
    }
  }

  @Test (enabled = false)
  public void testContactDeletion() {
    List<ContactData> before = app.getTo().list();
    app.getTo().selectContact(before.size() - 1);
    app.getTo().deleteContacts();
    app.getTo().homePage();
    List<ContactData> after = app.getTo().list();
    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }
}

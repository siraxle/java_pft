package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionsTests extends TestBase {

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
  public void testContactDeletion() {
    Contacts before = app.getTo().all();
    ContactData deletedContact = before.iterator().next();
    app.getTo().delete(deletedContact);
    Contacts after = app.getTo().all();
    assertEquals(after.size(), before.size() - 1);
    assertThat(after, equalTo(before.without(deletedContact)));
  }

}

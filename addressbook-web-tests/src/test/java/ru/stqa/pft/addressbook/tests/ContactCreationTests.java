package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test //(enabled = false)
  public void testContactCreation(){
    app.getTo().homePage();
    Contacts before = app.getTo().all();
    app.goTo().contactPage();
    ContactData contact = new ContactData()
            .withFirstName("Евгений").withLastName("Ефремов").withMiddleName("Витальевич")
            .withNickName("axle").withMobile("9522448961").withEmail("sir.axle@yandex.ru")
            .withGroup("test1").withNotes("it is test");
    app.getTo().create(contact);
    app.getTo().homePage();
    Contacts after = app.getTo().all();
    assertThat(before.size() + 1, equalTo(after.size()));
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }
}
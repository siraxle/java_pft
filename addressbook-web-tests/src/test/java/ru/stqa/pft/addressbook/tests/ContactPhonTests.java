package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhonTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().goToHomePage();
    if (!app.contact().isThereAContact()) {
      app.goTo().contactPage();
      app.contact().create(new ContactData()
              .withFirstName("Евгений").withLastName("Ефремов").withMiddleName("Витальевич")
              .withNickName("axle").withEmail("sir.axle@yandex.ru")
              .withGroup("test1").withNotes("it is test").withMobilePhone("9522448961").withHomePhone("4321").withWorkPhone("9876"));
    }
  }

  @Test
  public void testContactPhones() {
    app.goTo().goToHomePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getMobilePhone(), equalTo(cleaned(contactInfoFromEditForm.getMobilePhone())));
    assertThat(contact.getHomePhone(), equalTo(cleaned(contactInfoFromEditForm.getHomePhone())));
    assertThat(contact.getWorkPhone(), equalTo(cleaned(contactInfoFromEditForm.getWorkPhone())));
  }

  public String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }

}

package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().goToHomePage();
    if (! app.getContactHelper().isThereAContact()){
      app.getNavigationHelper().goToContactPage();
      app.getContactHelper().createContact(new ContactData("Евгений", "Ефремов", "Витальевич",
              "axle", "9522448961", "sir.axle@yandex.ru", "test1", "it is test"));
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("first_name","middle_name",
            "last_name","nick_name","12345","mail@mail.com",null,
            "qwerty"), false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
  }

}

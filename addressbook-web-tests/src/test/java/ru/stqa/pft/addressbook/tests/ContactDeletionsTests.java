package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;


public class ContactDeletionsTests extends TestBase {
  @Test
  public void testContactDeletion(){
    app.getNavigationHelper().goToHomePage();
    if (! app.getContactHelper().isThereAContact()){
      app.getNavigationHelper().goToContactPage();
      app.getContactHelper().createContact(new ContactData("Евгений", "Ефремов", "Витальевич",
              "axle", "9522448961", "sir.axle@yandex.ru", "test1", "it is test"));
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteContacts();
  }
}

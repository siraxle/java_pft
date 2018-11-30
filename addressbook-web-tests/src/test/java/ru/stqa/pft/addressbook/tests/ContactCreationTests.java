package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testAddContactToAddressBook(){
    app.getNavigationHelper().goToEditAddPage();
    app.getContactHelper().fillContactForm(new ContactData("Евгений", "Ефремов", "Витальевич",
            "axle", "9522448961", "sir.axle@yandex.ru","test1", "it is test"), true);
    app.getContactHelper().submitContactCreation();
  }
}

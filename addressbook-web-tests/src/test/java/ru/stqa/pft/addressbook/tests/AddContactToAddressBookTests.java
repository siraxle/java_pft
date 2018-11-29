package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class AddContactToAddressBookTests extends TestBase {

  @Test
  public void testAddContactToAddressBook(){
    app.getNavigationHelper().goToEditAddPage();
    app.getContactHelper().fillAddNew(new ContactData("Евгений", "Ефремов", "Витальевич",
            "axle", "9522448961", "sir.axle@yandex.ru", "it is test"));
    app.getContactHelper().clickEnterOnAddNewPage();
  }
}

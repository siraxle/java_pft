package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;


public class DeleteContactFromAddressBookTests extends TestBase {
  @Test
  public void testDeleteContactFromAddressBook(){
    app.getNavigationHelper().goToHomePage();
    app.getAddNewContactHelper().selectContact();
    app.getAddNewContactHelper().deleteContacts();
  }
}

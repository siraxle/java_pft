package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;


public class ContactDeletionsTests extends TestBase {
  @Test
  public void testContactDeletion(){
    app.getNavigationHelper().goToHomePage();
    if (! app.getContactHelper().isThereAContact()){
      app.getNavigationHelper().goToAddNewPage();
      ContactData contact = new ContactData( null, null,
              null, null, "sir.axle@yandex.ru", null, "it", null );
      app.getContactHelper().createContact(contact);
    }
    app.getNavigationHelper().goToHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().deleteContacts();
    app.getNavigationHelper().goToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(),before.size()-1);
    before.remove(before.size() - 1);
    Assert.assertEquals(after, before);
  }
}

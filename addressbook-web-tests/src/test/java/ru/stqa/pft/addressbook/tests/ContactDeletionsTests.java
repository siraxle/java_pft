package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;


public class ContactDeletionsTests extends TestBase {
  @Test
  public void testContactDeletion(){
    app.getNavigationHelper().goToHomePage();
    int before = app.getContactHelper().getContactCaunt();
    if (! app.getContactHelper().isThereAContact()){
      app.getNavigationHelper().goToAddNewPage();
      app.getContactHelper().createContact(new ContactData("Евгений", "Ефремов", "Витальевич",
              "axle", "9522448961", "sir.axle@yandex.ru", "test1", "it is test"));
    }
    app.getContactHelper().selectContact(0);
    app.getContactHelper().deleteContacts();
    app.getNavigationHelper().goToHomePage();
    int after = app.getContactHelper().getContactCaunt();
    Assert.assertEquals(after,before - 1);
  }
}

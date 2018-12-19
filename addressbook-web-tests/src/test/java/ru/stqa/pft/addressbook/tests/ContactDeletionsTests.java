package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;


public class ContactDeletionsTests extends TestBase {
  @Test (enabled = false)
  public void testContactDeletion(){
    app.goTo().goToHomePage();
    if (! app.getContactHelper().isThereAContact()){
      app.goTo().goToContactPage();
      app.getContactHelper().createContact(new ContactData("Евгений", "Ефремов", "Витальевич",
              "axle", "9522448961", "sir.axle@yandex.ru", "test1", "it is test"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size()-1);
    app.getContactHelper().deleteContacts();
    app.getContactHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }
}

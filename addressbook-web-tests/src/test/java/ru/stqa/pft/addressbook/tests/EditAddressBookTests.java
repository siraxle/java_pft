package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.HelperBase;
import ru.stqa.pft.addressbook.appmanager.SessionHelper;

public class EditAddressBookTests extends TestBase {

  @Test
  public void testAddContactToAddressBook(){
    app.getNavigationHelper().goToEditAddPage();
    HelperBase.type(By.name("firstname"),"Евгений");

  }


}

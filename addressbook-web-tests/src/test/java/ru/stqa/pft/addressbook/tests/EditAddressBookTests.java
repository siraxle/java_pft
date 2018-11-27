package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.AddNewData;

public class EditAddressBookTests extends TestBase {

  @Test
  public void testAddContactToAddressBook(){
    app.getNavigationHelper().goToEditAddPage();
    app.getAddNewHelper().fillAddNew(new AddNewData("Евгений", "Ефремов", "Витальевич",
            "axle", "9522448961", "sir.axle@yandex.ru", "1985", "it is test"));
    app.getAddNewHelper().clickEnterOnAddNewPage();

  }


}

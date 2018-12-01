package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {


  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().goToGroupPage();
    int before = app.getGroupHelper().getGroupCount();//колличество до добавления
    app.getGroupHelper().createGroup(new GroupData ("test1", null, null));
    app.getNavigationHelper().goToGroupPage();
    int after = app.getGroupHelper().getGroupCount();//колличество после добавления
    Assert.assertEquals(after,before + 1);
  }

}

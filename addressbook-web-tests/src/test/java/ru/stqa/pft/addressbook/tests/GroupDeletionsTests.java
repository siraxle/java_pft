package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeletionsTests extends TesteBase{
    
    @Test
    public void testGroupDeletion() {
        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnToGroupPage();
    }

}

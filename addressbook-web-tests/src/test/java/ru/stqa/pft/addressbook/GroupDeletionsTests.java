package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class GroupDeletionsTests extends TesteBase{
    
    @Test
    public void testGroupDeletion() {
        goToGroupPage();
        selectGroup();
        deleteSelectedGroups("delete");
        returnToGroupPage();
    }

}

package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.Iterator;

public class ContactAddGroup  extends TestBase  {

    @Test
    public void testAddContactInGroup (){
        app.contact().homePage();
        Contacts allContacts = app.db().contacts();
        ContactData checkedContact = allContacts.iterator().next();
        app.contact().selectContactById(checkedContact.getId());
        app.contact().addToGroup();
        app.contact().homePage();
//        System.out.println("ID контакта из интерфейса - " + checkedContact.getId());
        String nameOfGroupFromDetails = app.contact().getIdNameFromDetailPage(checkedContact);
        System.out.println("данные по группе из Details - " + nameOfGroupFromDetails);
        app.contact().homePage();
        Groups contactGroups = checkedContact.getGroups();
        Iterator<GroupData> iter = contactGroups.iterator();
        ArrayList<String> namesOfGroups = new ArrayList<String>();
        while (iter.hasNext()) {
           GroupData gd =  iter.next();
           namesOfGroups.add(gd.getName());
        }
        Assert.assertTrue(namesOfGroups.contains(nameOfGroupFromDetails));
    }

}

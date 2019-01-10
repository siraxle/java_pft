package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContacts() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(";");
            list.add(new Object[]{new ContactData().withFirstName(split[0]).withLastName(split[1]).withMiddleName(split[2])
                        .withNickName(split[3]).withEmail(split[4]).withGroup(split[5]).withNotes(split[6])
                        .withHomePhone(split[7]).withMobilePhone(split[8]).withWorkPhone(split[9])
                        .withPhoto(new File(split[10]))});
            line = reader.readLine();

        }
        return list.iterator();
    }


    @Test(dataProvider = "validContacts")//(enabled = false)
    public void testContactCreation(ContactData contact) {
        app.contact().homePage();
        Contacts before = app.contact().all();
        app.goTo().contactPage();
        app.contact().create(contact);
        app.contact().homePage();
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }


    @Test//(enabled = false)
    public void testCurrentDir() {
        File currentDir = new File(".");
        System.out.println(currentDir.getAbsolutePath());
        File photo = new File("src/test/resources/file.jpg");
        System.out.println(photo.getAbsolutePath());
        System.out.println(photo.exists());
    }

}

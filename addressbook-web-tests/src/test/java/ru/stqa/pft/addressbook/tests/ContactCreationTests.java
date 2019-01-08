package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContacts() {
        List<Object[]> list = new ArrayList<Object[]>();
        File photo1 = new File("src/test/resources/file.jpg");
        File photo2 = new File("src/test/resources/monkey.png");
        list.add(new Object[]{new ContactData().withFirstName("Ксения").withLastName("Тараканова").withMiddleName("Викторовна")
                .withNickName("spring").withEmail("spring@mail.ru").withGroup("test2").withNotes("note1").withHomePhone("1234")
                .withWorkPhone("4321").withMobilePhone("8765").withPhoto(photo2)});
        list.add(new Object[]{new ContactData().withFirstName("Евгений").withLastName("Ефремов").withMiddleName("Витальевич")
                .withNickName("axle").withEmail("axle@mail.ru").withGroup("test1").withNotes("note2").withHomePhone("1234")
                .withWorkPhone("4321").withMobilePhone("8765").withPhoto(photo1)});
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

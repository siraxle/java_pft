package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test//(enabled = false)
  public void testContactCreation() {
    app.contact().homePage();
    Contacts before = app.contact().all();
    app.goTo().contactPage();
    File photo = new File("src/test/resources/file.jpg");
    ContactData contact = new ContactData()
            .withFirstName("Евгений").withLastName("Ефремов").withMiddleName("Витальевич")
            .withNickName("axle").withEmail("sir.axle@yandex.ru")
            .withGroup("test1").withNotes("it is test").withHomePhone("1")
            .withMobilePhone("2").withWorkPhone("3").withPhoto(photo);
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

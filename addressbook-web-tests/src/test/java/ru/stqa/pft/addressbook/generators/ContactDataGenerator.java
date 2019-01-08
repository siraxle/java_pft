package ru.stqa.pft.addressbook.generators;

import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
    public static void main (String args[]) throws IOException {
        int count = Integer.parseInt(args[0]);
        File file = new File(args[1]);

        List<ContactData> contacts = generateContacts(count);
        save(contacts, file);
    }

    private static void save(List<ContactData> contacts, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (ContactData contact : contacts){
            writer.write(String.format(("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n"), contact.getFirstName(), contact.getLastName(),
                    contact.getMiddleName(), contact.getNickName(), contact.getEmail(), contact.getGroup(),
                    contact.getNotes(), contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(),
                    contact.getPhoto()));
        }
        writer.close();
    }

    private static List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        File photo = new File("src/test/resources/file.jpg");
        for (int i = 0; i < count; i++){
            contacts.add(new ContactData().withFirstName("Евгений").withLastName("Ефремов").withMiddleName("Витальевич")
                    .withNickName("axle").withEmail("sir.axle@yandex.ru")
                    .withGroup("test1").withNotes("it is test").withHomePhone("1")
                    .withMobilePhone("2").withWorkPhone("3").withPhoto(photo));
        }
        return contacts;
    }
}

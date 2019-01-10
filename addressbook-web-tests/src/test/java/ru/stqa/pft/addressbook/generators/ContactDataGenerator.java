package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main (String args[]) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
       JCommander jCommander = new JCommander(generator);
       try {
           jCommander.parse(args);
       } catch (ParameterException e){
           jCommander.usage();
           return;
       }
        generator.run();

    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if (format.equals("csv")){
            saveAsCsv(contacts, new File(file));
        } else if (format.equals("xml")){
            saveAsXml(contacts, new File(file));
        } else if (format.equals("json")){
            saveAsJson(contacts, new File(file));
        } else {
            System.out.println("Unrecognized format " + format);
        }

    }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        Writer writer = new FileWriter(file);
        writer.write(json);
        writer.close();
    }

    private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        String xml = xstream.toXML(contacts);
        Writer writer = new FileWriter(file);
        writer.write(xml);
        writer.close();
    }

    private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (ContactData contact : contacts){
            writer.write(String.format(("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n"), contact.getFirstName(), contact.getLastName(),
                    contact.getMiddleName(), contact.getNickName(), contact.getEmail(), contact.getGroup(),
                    contact.getNotes(), contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(),
                    contact.getPhoto()));
        }
        writer.close();
    }

    private List<ContactData> generateContacts(int count) {
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

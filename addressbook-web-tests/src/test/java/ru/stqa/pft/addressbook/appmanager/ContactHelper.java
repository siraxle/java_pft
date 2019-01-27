package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(ChromeDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.name("submit"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNickName());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());

        if (creation) {
            if (contactData.getGroups().size() > 0) {
                Assert.assertTrue(contactData.getGroups().size() == 1);
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

        type(By.name("email"), contactData.getEmail());
        type(By.name("notes"), contactData.getNotes());
        attach(By.name("photo"), contactData.getPhoto());
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void deleteContacts() {
        click(By.cssSelector("input[value='Delete']"));
        wd.switchTo().alert().accept();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteContacts();
        contactCache = null;
        homePage();
    }

    public void modify(ContactData contact) {
        selectContactById(contact.getId());
        initContactModificationById(contact.getId());
    }

    public void addToGroup() {
        click(By.name("add"));
    }

    public void initContactModificationById(int id) {
        click(By.cssSelector(String.format("a[href='edit.php?id=%s']", id)));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void homePage() {
        click(By.xpath("//*[@id=\"nav\"]/ul/li[1]/a"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void create(ContactData contact) {
        fillContactForm(contact, true);
        submitContactCreation();
        contactCache = null;
    }

    public Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> rows = wd.findElements(By.name("entry"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String email = row.findElement(By.tagName("input")).getAttribute("accept");
            String lastname = cells.get(1).getText();
            String firstname = cells.get(2).getText();
            String allPhones = cells.get(5).getText();
            ContactData contact = new ContactData().withId(id).withEmail(email).withFirstName(firstname).withLastName(lastname)
                    .withAllPhones(allPhones);
            contactCache.add(contact);
        }
        return new Contacts(contactCache);
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        wd.navigate().back();
        return contact = new ContactData().withId(contact.getId()).withEmail(contact.getEmail()).withFirstName(firstName).withLastName(lastname).
                withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work);
    }

    public ContactData infoFromDetailPage(ContactData contact) {
        initContactDetailsById(contact.getId());
        String[] fio = wd.findElement(By.cssSelector("#content > b")).getText().split("\\s");
        String email = wd.findElement(By.cssSelector("#content > a")).getText();
        String[] info = wd.findElement(By.xpath("//*[@id=\"content\"]")).getText().split("\\n");
        String[] home = info[4].split("\\s");
        String homePhone = home[1];
        String[] mobile = info[5].split("\\s");
        String mobilePhone = mobile[1];
        String[] work = info[6].split("\\s");
        String workPhone = work[1];
        return contact = new ContactData().withFirstName(fio[0]).withLastName(fio[2])
                .withWorkPhone(workPhone).withMobilePhone(mobilePhone).withHomePhone(homePhone).withEmail(email);
    }


    public String getIdNameFromDetailPage(ContactData contact) {
        initContactDetailsById(contact.getId());
        String idFromDetails = wd.findElement(By.cssSelector("#content > i > a")).getText();
        String groupId = wd.findElement(By.cssSelector("#content > i")).getText();
        return idFromDetails;
    }

    private void initContactDetailsById(int id) {
        click(By.cssSelector(String.format("a[href='view.php?id=%s']", id)));
    }

}

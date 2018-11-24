package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupHelper {
  private ChromeDriver wd;

  public GroupHelper(ChromeDriver wd) {
    this.wd = wd;
  }

  public void returnToGroupPage() {
    click(By.linkText("group page"));
  }

  public void submitGroupCreation() {
    click(By.name("submit"));
  }

  private void click(By locator) {
    wd.findElement(locator).click();
  }

  public void fillGroupForm(GroupData groupData) {
    type(By.name("group_name"), groupData.getName());
    type(By.name("group_header"), groupData.getHeader());
    type(By.name("group_footer"), groupData.getFooter());
  }

  private void type(By locator, String text) {
    click(locator);
    wd.findElement(locator).clear();
    wd.findElement(locator).sendKeys(text);
  }

  public void initGroupCreation() {
    click(By.name("new"));

  }

  public void deleteSelectedGroups(String delete) {
    click(By.name(delete));
  }

  public void selectGroup() {
      if (!wd.findElement(By.name("selected[]")).isSelected()) {
        click(By.name("selected[]"));
      }
  }
}

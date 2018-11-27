package ru.stqa.pft.addressbook.model;

public class AddNewData {
  private final String firstName;
  private final String middleName;
  private final String lastName;
  private final String nickName;
  private final String mobile;
  private final String email;
  private final String byear;
  private final String notes;

  public AddNewData(String firstName, String middleName, String lastName, String nickName, String mobile, String email, String byear, String notes) {
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.nickName = nickName;
    this.mobile = mobile;
    this.email = email;
    this.byear = byear;
    this.notes = notes;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getNickName() {
    return nickName;
  }

  public String getMobile() {
    return mobile;
  }

  public String getEmail() {
    return email;
  }

  public String getByear() {
    return byear;
  }

  public String getNotes() {
    return notes;
  }
}

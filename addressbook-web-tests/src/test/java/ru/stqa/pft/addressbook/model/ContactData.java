package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
  private int id;
  private final String firstName;
  private final String middleName;
  private final String lastName;
  private final String nickName;
  private final String mobile;
  private final String email;
  private String group;
  private final String notes;

  public ContactData(int id, String firstName, String middleName, String lastName, String nickName, String mobile, String email, String group,String notes) {
    this.id = id;
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.nickName = nickName;
    this.mobile = mobile;
    this.email = email;
    this.group = group;
    this.notes = notes;
  }

  public ContactData(String firstName, String middleName, String lastName, String nickName, String mobile, String email, String group,String notes) {
    this.id = 0;
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.nickName = nickName;
    this.mobile = mobile;
    this.email = email;
    this.group = group;
    this.notes = notes;
  }

  public int getId() {
    return id;
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

  public String getNotes() {
    return notes;
  }

  public String getGroup() {
    return group;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", email='" + email + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id &&
            Objects.equals(email, that.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, email);
  }
}

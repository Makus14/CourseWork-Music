package by.nyurush.music.entity;

import java.util.Objects;

public class User extends Account {
    private String firstName;
    private String lastName;
    private String email;
    private Boolean subscription;

    public User(Integer id) {
        super(id);
    }

    public User(Integer id, String login, String password, AccountRole role, String firstName, String lastName, String email, Boolean subscription) {
        super(id, login, password, role);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.subscription = subscription;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getSubscription() {
        return subscription;
    }

    public void setSubscription(Boolean subscription) {
        this.subscription = subscription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(email, user.email) &&
                Objects.equals(subscription, user.subscription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstName, lastName, email, subscription);
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", subscription=" + subscription +
                '}';
    }
}

package backend.gatsby.models;

import javax.persistence.Column;
import java.util.List;

public class AttendeeUserItemDTO {
    Integer attendee_id;
    private String name;
    private int age;
    private double rating;
    private String email;
    private String password;


    /**
     * Address of User
     */
    @Column
    private String address;

    /**
     * username of User
     */
    @Column(nullable = false)
    private String username;

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username to a new String value
     * @param username new username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the user password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the passowrd to a new String value
     * @param password new password
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * @return name of the user
     */
    public String getName() {
        return name;
    }
    /**
     * @return email of the user
     */
    public String getEmail() {
        return email;
    }
    /**
     * @return age of the user
     */
    public int getAge() {
        return age;
    }
    /**
     * @return rating of the user
     */
    public double getRating() {
        return rating;
    }
    /**
     * @return address of the user
     */
    public String getAddress() {
        return address;
    }
    /**
     * Sets name of user
     * @param newName new name
     */
    public void setName(String newName) {
        name = newName;
    }
    /**
     * Sets age of user
     * @param newAge new age
     */
    public void setAge(int newAge) {
        age = newAge;
    }
    /**
     * Sets email of user
     * @param newEmail new email
     */
    public void setEmail(String newEmail) {
        email = newEmail;
    }
    /**
     * Sets address of user
     * @param newAddress new address
     */
    public void setAddress(String newAddress) {
        address = newAddress;
    }
    /**
     * Sets rating of user
     * @param newRating new rating
     */
    public void setRating(Double newRating) {
        rating = newRating;
    }
    /**
     * @return user's id
     */
    public Integer getId() {
        return attendee_id;
    }
    /**
     * Sets id of user
     * @param id new id
     */
    public void setId(Integer id) {
        this.attendee_id = id;
    }
}

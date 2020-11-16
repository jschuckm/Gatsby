package backend.gatsby.models;

import backend.gatsby.entities.AttendeeUser;
import backend.gatsby.entities.HostUser;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class EventItemDTO {
    Integer id;
    private String name;
    private String description;
    private Date date;
    private float fee;
    private HostUser hostProfile;
    private String address;
    private boolean isPublic;
    private int capacity;

    /**
     * @return name of te event
     */
    public String getName() {
        return name;
    }
    /**
     * @return date of the event
     */
    public Date getDate() {
        return date;
    }
    /**
     *
     * @return fee to enter the event
     */
    public float getFee() {
        return fee;
    }
    /**
     *
     * @return address of the event
     */
    public String getAddress() {
        return address;
    }
    /**
     *
     * @return whether event is public or not
     */
    public boolean getIsPublic() {
        return isPublic;
    }
    /**
     *
     * @return maximum number of attendees
     */
    public int getCapacity() {
        return capacity;
    }
    /**
     *
     * @return user profile of the host
     */
    @JsonIgnore
    public HostUser getHost() {
        return hostProfile;
    }
    /**
     * Changes the host to h
     * @param h new host
     */
    public void setHost(HostUser h) {
        hostProfile = h;
    }
    /**
     * Changes the name of the event
     * @param n new name
     */
    public void setName(String n) {
        name = n;
    }
    /**
     * Changes the date of the event
     * @param d new date
     */
    public void setDate(Date d) {
        date = d;
    }
    /**
     * Changes the entrance fee for the event
     * @param f the new fee
     */
    public void setFee(float f) {
        fee = f;
    }
    /**
     * Changes the address of the event
     * @param a the new address
     */
    public void setAddress(String a) {
        address = a;
    }
    /**
     * Changes public status of event
     * @param p new boolean variable that determines if the event is public
     */
    public void setIsPublic(boolean p) {
        isPublic = p;
    }
    /**
     * Changes the capacity of the event
     * @param c the new capacity
     */
    public void setCapacity(int c) {
        capacity = c;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

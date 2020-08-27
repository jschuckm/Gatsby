package experiments;
import java.util.ArrayList;
/*
 * This is one of the three categories of users indicated in the project proposal, the party atendee.
 * The party attendee will have a payment status (if they paid for the party and can enter), a rating, friends
 * This is just a test file in the experiments directory
 */
public class PartyAtendee {
	private boolean payStatus;
	private float rating;
	private int quantRatings; //how many times this user has been rated before
	private ArrayList<PartyAtendee> friends;
	public PartyAtendee() {
		payStatus = false;
		rating = 0;
		quantRatings = 0;
		friends = new ArrayList<PartyAtendee>();
	}
	
	public boolean getPayStatus() {
		return payStatus;
	}
	
	public void pay() {
		payStatus = true;
	}
	
	public float getRating() {
		return rating;
	}
	
	public void newRating(int newRating) {
		 quantRatings ++;
		 rating = ((rating*quantRatings-1)+newRating)/quantRatings;
	}
	
	public ArrayList<PartyAtendee> getFriends(){
		return friends;
	}
	
	public boolean addFriend(PartyAtendee newFriend) {
		return friends.add(newFriend);
	}
}

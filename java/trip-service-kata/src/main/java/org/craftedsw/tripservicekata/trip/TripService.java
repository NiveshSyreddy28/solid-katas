package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSessionInterface;

import java.util.ArrayList;
import java.util.List;

public class TripService {
	private final TripDAOInterface tripDAO;

	public TripService(TripDAOInterface tripDAO) {
		this.tripDAO = tripDAO;
	}

	List<Trip> getTripsByUser(User user, UserSessionInterface session) {
		User loggedUser = session.getLoggedUser();
		if(loggedUser == null) {
			throw new UserNotLoggedInException();
		}
		if(isFriend(user, loggedUser)) {
			return tripDAO.findTripsByUser(user);
		}
		return new ArrayList<>();
}

	private boolean isFriend(User user, User maybeFriend) {
		List<User> friends = user.getFriends();
		for (User friend: friends) {
			if (friend.equals(maybeFriend)){
				return true;
			}
		}
		return false;
	}

}

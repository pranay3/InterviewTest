package org.kcpranay.personal;

import java.util.*;

public class WishlistManager {

    private final Map<String, List<String>> userWishLists;
    public WishlistManager() {
        userWishLists = new HashMap<>();
    }


    public void addWishList(final String user, final List<String> wishList) {
        userWishLists.put(user, wishList);
    }

    public boolean has_mutual_first_choice(final String user) {
        return has_mutual_pair_for_rank(user, 0);
    }

    public boolean has_mutual_pair_for_rank(final String user, final Integer rank) {
        if(!userWishLists.containsKey(user)) {
            throw new IllegalArgumentException("User "+user+" doesn't have any wishlist");
        }
        if(userWishLists.get(user).size()<rank+1) {
            throw new IllegalArgumentException("User "+user+" doesn't have any choice for rank " + rank);
        }
        String rankedChoiceUser = userWishLists.get(user).get(rank);
        if(!userWishLists.containsKey(rankedChoiceUser) || userWishLists.get(rankedChoiceUser).size()<rank+1) {
            return false;
        }

        return userWishLists.get(rankedChoiceUser).get(rank).equals(user);
    }

    List<String> changed_pairings(final String user, final Integer choiceToBump) {
        if(!userWishLists.containsKey(user)) {
            throw new IllegalArgumentException("User "+user+" doesn't have any wishlist");
        }
        if(userWishLists.get(user).size()<choiceToBump+1) {
            throw new IllegalArgumentException("User "+user+" doesn't have any choice for rank " + choiceToBump);
        }
        List<String> modifiedPairingUsers = new ArrayList<>();
        if(choiceToBump == 0) {
            return modifiedPairingUsers;
        }
        modifiedPairingUsers.addAll(findUsersWithMutualPairings(user, choiceToBump ));

        ArrayList<String> userWishlist = (ArrayList<String>) userWishLists.get(user);
        String bumpedUser = userWishlist.get(choiceToBump);
        userWishlist.add(choiceToBump, userWishlist.get(choiceToBump-1));
        userWishlist.add(choiceToBump - 1 , bumpedUser);

        modifiedPairingUsers.addAll(findUsersWithMutualPairings(user, choiceToBump ));

        bumpedUser = userWishlist.get(choiceToBump);
        userWishlist.add(choiceToBump, userWishlist.get(choiceToBump-1));
        userWishlist.add(choiceToBump - 1 , bumpedUser);

        return modifiedPairingUsers;
    }

    private List<String> findUsersWithMutualPairings(final String user, final Integer bumpedUser) {
        List<String> users = new ArrayList<>();
        if(has_mutual_pair_for_rank(user, bumpedUser)) {
            users.add(userWishLists.get(user).get(bumpedUser));
        }
        if(has_mutual_pair_for_rank(user, bumpedUser-1)) {
            users.add(userWishLists.get(user).get(bumpedUser-1));
        }
        return users;
    }
}

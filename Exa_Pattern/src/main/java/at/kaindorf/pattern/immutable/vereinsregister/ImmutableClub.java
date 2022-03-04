package at.kaindorf.pattern.immutable.vereinsregister;

import java.util.ArrayList;
import java.util.List;

public final class ImmutableClub {

    private final int secretNumber;
    private final List<Member> memberList;
    private final String clubName;

    public ImmutableClub(int secretNumber, List<Member> memberList, String clubName) {
        this.secretNumber = secretNumber;
        this.memberList = new ArrayList<>(memberList);
        this.clubName = clubName;
    }

    public int getSecretNumber() {
        return secretNumber;
    }

    public List<Member> getMemberList() {
        return new ArrayList<>(memberList);
    }

    public String getClubName() {
        return clubName;
    }

    @Override
    public String toString() {
        return "ImmutableClub{" +
                "secretNumber=" + secretNumber +
                ", memberList=" + memberList +
                ", clubName='" + clubName + '\'' +
                '}';
    }
}

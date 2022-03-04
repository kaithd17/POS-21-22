package at.kaindorf.pattern.immutable.vereinsregister;

import java.util.List;

public class Club {

    private int secretNumber;
    private List<Member> memberList;
    private String clubName;

    public Club(int secretNumber, List<Member> memberList, String clubName) {
        this.secretNumber = secretNumber;
        this.memberList = memberList;
        this.clubName = clubName;
    }

    public int getSecretNumber() {
        return secretNumber;
    }

    public void setSecretNumber(int secretNumber) {
        this.secretNumber = secretNumber;
    }

    public List<Member> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<Member> memberList) {
        this.memberList = memberList;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    @Override
    public String toString() {
        return "Club{" +
                "secretNumber=" + secretNumber +
                ", memberList=" + memberList +
                ", clubName='" + clubName + '\'' +
                '}';
    }
}

package at.kaindorf.pattern.immutable.vereinsregister;

import java.util.ArrayList;
import java.util.List;

public class ImmutableTester {

    public static void main(String[] args) {
        List<Member> memberList = new ArrayList<>();
        memberList.add(new Member("Alexander", "Kirschner"));
        memberList.add(new Member("Thomas", "Kainz"));

        Club club = new Club(1, memberList, "test1");
        ImmutableClub immutableClub = new ImmutableClub(2, memberList, "test2");
        System.out.println(club);
        System.out.println(immutableClub);
        System.out.println("\n Add member:" +
                "");
        club.getMemberList().add(new Member("Matthias", "Horwath"));
        immutableClub.getMemberList().add(new Member("Matthias", "Horwath"));
        System.out.println(club);
        System.out.println(immutableClub);
    }
}

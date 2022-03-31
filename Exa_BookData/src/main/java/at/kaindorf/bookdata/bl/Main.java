package at.kaindorf.bookdata.bl;

import at.kaindorf.bookdata.pojos.Publisher;
import at.kaindorf.bookdata.xml.XML_Access;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Publisher> publishers = new ArrayList<>();
        publishers = XML_Access.getData();
        System.out.println(publishers);
    }
}

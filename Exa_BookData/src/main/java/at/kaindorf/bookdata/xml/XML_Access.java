package at.kaindorf.bookdata.xml;

import at.kaindorf.bookdata.pojos.Author;
import at.kaindorf.bookdata.pojos.Book;
import at.kaindorf.bookdata.pojos.Books;
import at.kaindorf.bookdata.pojos.Publisher;
import jakarta.xml.bind.JAXB;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class XML_Access {
    public static final Path path = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "books.xml");

    public static List<Publisher> getData() {
        List<Publisher> publishers = JAXB.unmarshal(path.toFile(), Books.class).getPublishers();
        System.out.println(publishers);

        Set<Author> authorSet = new HashSet<>();
        Set<Book> bookSet = new HashSet<>();

        //Get all books and authors
        publishers.forEach(publisher -> {
            publisher.getBooks().forEach(book -> {
                bookSet.add(book);
                book.getAuthors().forEach(author -> {
                    authorSet.add(author);
                });
            });
        });

        publishers.forEach(publisher -> {
            publisher.getBooks().forEach(book -> {
                if (publisher.getBooks().contains(book)) {
                    //remove old book
                    publisher.getBooks().removeIf(book1 -> book1.equals(book));

                    //find the same book with the new reference
                    final Book newBook = bookSet.stream().filter(book1 -> book1.equals(book)).findFirst().get();

                    //do the same thing with authors
                    book.getAuthors().forEach(author -> {
                        if (book.getAuthors().contains(author)) {
                            newBook.getAuthors().removeIf(author1 -> author1.equals(author));
                            Author newAuthor = authorSet.stream().filter(author1 -> author1.equals(author)).findFirst().get();
                            newBook.getAuthors().add(author);
                        }
                    });
                    //add new book to the list
                    publisher.getBooks().add(newBook);
                    newBook.setPublisher(publisher);
                }
            });
        });


        return publishers;
    }
}

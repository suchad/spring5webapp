package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repository.AuthorRepository;
import guru.springframework.spring5webapp.repository.BookRepository;
import guru.springframework.spring5webapp.repository.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class bootstrap implements CommandLineRunner {



    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public bootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher bharatPub = new Publisher("BHarat Publisher","This Street","Delhi","India");
        publisherRepository.save(bharatPub);

        Author eric = new Author("Eric", "Ovens");
        Book springbook = new Book("Spring in 30 minutes","123");
        eric.getBook().add(springbook);
        springbook.getAuthor().add(eric);

        springbook.setPublisher(bharatPub);
        bharatPub.getBooks().add(springbook);

        authorRepository.save(eric);
        bookRepository.save(springbook);
        publisherRepository.save(bharatPub);

        Author bob = new Author("Bob", "Johnson");
        Book divine = new Book("Divine Book", "456");
        //Publisher tramPub = new Publisher("Tramer Publisher","That Street", "Mumbai", "India");
        bob.getBook().add(divine);
        divine.getAuthor().add(bob);

        divine.setPublisher(bharatPub);
        bharatPub.getBooks().add(divine);

        authorRepository.save(bob);
        bookRepository.save(divine);
        publisherRepository.save(bharatPub);

        System.out.println("Number of Books: " +bookRepository.count());
//        System.out.println("Book Repo Values: " +bookRepository.findAll() + bookRepository.getClass());
        System.out.println("Author Repo Values: Count: " + authorRepository.count() + "Find All: " +
        authorRepository.findAll() + "Class: " + authorRepository.getClass() + "HashCode: " + authorRepository.hashCode());
        System.out.println("Publisher Repository: " + publisherRepository.toString() + "--" +
                publisherRepository.count() + "Books - " + bharatPub.getBooks().size());

    }
}

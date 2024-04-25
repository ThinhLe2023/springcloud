package contacts;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
public class RestClientApplication implements CommandLineRunner {
	private RestTemplate restTemplate = new RestTemplate();

	public static void main(String[] args) {
		SpringApplication.run(RestClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String serverUrl = "http://localhost:8080/books";

		// add book 1
		restTemplate.postForLocation(serverUrl, new Book(1, "Frank","Browns", "isbn1",
				1.02));
		// add book 2
		restTemplate.postForLocation(serverUrl, new Book(2, "John","Doe", "isbn 2",
				2.02));
		// get book 1
		Book book= restTemplate.getForObject(serverUrl+"/{isbn}", Book.class, "isbn1");
		System.out.println("----------- get book-----------------------");
		System.out.println(book.getTitle()+" "+book.getAuthor());
        // get all
		List<Book> books = restTemplate.getForObject(serverUrl, List.class);
		System.out.println("----------- get all Books-----------------------");
		System.out.println(books);

		// delete John
		restTemplate.delete(serverUrl+"/{isbn}", "isbn1");

		// update frank
		book.setAuthor("New author");
		restTemplate.put(serverUrl+"/{isbn}", book, book.getIsbn());

		// get all
		List<Book> books1 = restTemplate.getForObject(serverUrl, List.class);
		System.out.println("----------- get all Books-----------------------");
		System.out.println(books1);
	}

}

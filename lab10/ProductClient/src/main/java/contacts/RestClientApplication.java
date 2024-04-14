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
		String serverUrl = "http://localhost:8902/product";
		String productQueryUrl = "http://localhost:8901/product";
		String stockUrl = "http://localhost:8900/stock";


		// add Product 1
		restTemplate.postForLocation(serverUrl, new Product("Frank","56", 1.2,0));
		// add Product 2
		restTemplate.postForLocation(serverUrl, new Product( "John","57",2.02,0));

		// add product 1 - stock: 10
		restTemplate.postForLocation(stockUrl, new Stock("57",10));
		// add product 2 - stock: 5
		restTemplate.postForLocation(stockUrl, new Stock("56",5));

		//sleep 15 seconds
		Thread.sleep(15000);

		// get Product 1 detail from ProductQueryService
		Product book = restTemplate.getForObject(productQueryUrl+"/{isbn}", Product.class, "56");
		Product book1 = restTemplate.getForObject(productQueryUrl+"/{isbn}", Product.class, "57");
		System.out.println("----------- get Product -----------------------");
		System.out.println(book);
		System.out.println(book1);
	}

}

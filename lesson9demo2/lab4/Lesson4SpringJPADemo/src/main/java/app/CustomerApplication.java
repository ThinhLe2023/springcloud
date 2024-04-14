package app;

import java.util.Arrays;
import java.util.List;

import app.domain.Address;
import app.domain.CreditCard;
import app.domain.Student;
import app.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import app.repositories.CustomerRepository;
import app.domain.Customer;

@SpringBootApplication
public class CustomerApplication implements CommandLineRunner{
	
	@Autowired
	CustomerRepository customerrepository;

	@Autowired
	StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}

	@Override
	public void run(String... args) {
		// create customer
		Customer customer = new Customer(101,"John doe", "johnd@acme.com", "0622341678");
		CreditCard creditCard = new CreditCard("12324564321", "Visa", "11/23");
		customer.setCreditCard(creditCard);
		customerrepository.save(customer);
		customer = new Customer(109,"John Jones", "jones@acme.com", "0624321234");
		creditCard = new CreditCard("657483342", "Visa", "09/23");
		customer.setCreditCard(creditCard);
		customerrepository.save(customer);
		customer = new Customer(66,"James Johnson", "jj123@acme.com", "068633452");
		creditCard = new CreditCard("99876549876", "MasterCard", "01/24");
		customer.setCreditCard(creditCard);
		customerrepository.save(customer);

//get customers
		System.out.println(customerrepository.findById(66).get());
		System.out.println(customerrepository.findById(101).get());
		System.out.println("-----------All customers ----------------");
		System.out.println(customerrepository.findAll());
		//update customer
		customer = customerrepository.findById(101).get();
		customer.setEmail("jd@gmail.com");
		customerrepository.save(customer);
		System.out.println("-----------find by phone ----------------");
		System.out.println(customerrepository.findByPhone("0622341678"));
		System.out.println("-----------find customers with a certain type of creditcard ----------------");
		List<Customer> customers = customerrepository.findByCreditCardType("Visa");
		for (Customer cust : customers){
			System.out.println(cust);
		}

		System.out.println("-----------find by name ----------------");
		System.out.println(customerrepository.findByName("John doe"));


		//create 5 student
		Address address = new Address("Street 1", "city 1", 12345);
		Address address2 = new Address("Street 2", "city 2", 12342);
		Student s = new Student("tom",123456789, "a@gmail.com", address);
		Student s1 = new Student("tom1",1234567891, "a1@gmail.com", address2);
		Student s2 = new Student("tom2",1234567892, "a2@gmail.com", address2);
		Student s3 = new Student("tom3",1234567893, "a3@gmail.com", address);
		Student s4 = new Student("tom4",1234567894, "a4@gmail.com", address);
		studentRepository.saveAllAndFlush(Arrays.asList(s,s1,s2,s3,s4));

		//get all students
		System.out.println("-----------get all students ----------------");
		System.out.println(studentRepository.findAll());

		System.out.println("-----------get students by name - tom2 ----------------");
		System.out.println(studentRepository.findByName("tom2"));

		System.out.println("-----------get students by phone ----------------");
		System.out.println(studentRepository.findByPhoneNumber(1234567893));

		System.out.println("-----------get students by city ----------------");
		System.out.println(studentRepository.findByAddressCity("city 2"));
	}

}

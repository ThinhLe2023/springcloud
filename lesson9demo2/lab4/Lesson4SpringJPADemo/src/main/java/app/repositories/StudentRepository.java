package app.repositories;

import app.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student findByName(String tom2);

    Student findByPhoneNumber(int number);

    @Query("select s from Student s where s.address.city = :city")
    List<Student> findByAddressCity(String city);
}

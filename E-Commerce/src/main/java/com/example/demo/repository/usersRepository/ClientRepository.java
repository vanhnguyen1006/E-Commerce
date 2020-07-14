package com.example.demo.repository.usersRepository;

import com.example.demo.entity.user.Client;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends PagingAndSortingRepository<Client, Long> {
    List<Client> findAllByName(String name);
    Client findByPhone(String phone);
    List<Client> findAllByAge(int age);
    List<Client> findAllByAgeLessThan(int age);
    List<Client> findAllByAgeGreaterThan(int age);
    List<Client> findAllByAgeBetween(int start,int end);
    List<Client> findAllByStreet(String street);
    List<Client> findAllByCity(String city);
    List<Client> findAllByStreetAndCity(String street,String city);
    Optional<Client> findByEmail(String email);
    Optional<Client> findByNameOrEmail(String name, String email);
    Optional<Client> findByName(String name);
    Optional<Client> findByUserName(String userName);
}

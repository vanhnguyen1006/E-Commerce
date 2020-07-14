package com.example.demo.repository.salesRepository;

import com.example.demo.entity.sales.UserDTO;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserDTO, Long> {
    UserDTO findByUserName(String name);
}

package com.example.demo.repository.usersRepository;

import com.example.demo.entity.user.VerifyUser;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerifyUserRepository extends PagingAndSortingRepository<VerifyUser, Long> {
    Optional<VerifyUser> findAllByEmail(String email);
}

package com.boot.pla.querydsl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.pla.querydsl.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
}

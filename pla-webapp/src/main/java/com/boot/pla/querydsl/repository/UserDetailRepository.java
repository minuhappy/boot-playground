package com.boot.pla.querydsl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.pla.querydsl.entity.UserDetail;

public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {
}

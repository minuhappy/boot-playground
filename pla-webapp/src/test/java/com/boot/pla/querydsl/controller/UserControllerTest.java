package com.boot.pla.querydsl.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class UserControllerTest {

    @Autowired
    private UserController userController;

    @Test
    void create() {
        userController.create(null);
        log.debug("END");
    }

    @Test
    void selectByLoginId() {
    }

    @Test
    void search() {
        userController.search("profile_1", null);
    }
}
package com.boot.pla.querydsl.controller;

import java.util.Date;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.boot.pla.querydsl.entity.User;
import com.boot.pla.querydsl.entity.UserDetail;
import com.boot.pla.querydsl.repository.UserDetailRepository;
import com.boot.pla.querydsl.repository.UserRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

import static com.boot.pla.querydsl.entity.QUser.user;
import static com.boot.pla.querydsl.entity.QUserDetail.userDetail;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final JPAQueryFactory queryFactory;
    private final UserRepository userRepository;
    private final UserDetailRepository userDetailRepository;

    @PostMapping
    public User create(@RequestBody User user) {
//        return userRepository.save(user);

        for (int i = 0; i < 100; i++) {
            User u = new User()
                .setProfileId("profile_" + i);

            userRepository.save(u);

            UserDetail ud = new UserDetail()
                .setUser(u)
                .setName("Name_" + i)
                .setAddress("Address_" + i)
                .setBirthDate(new Date())
                .setEmail("Email_" + i)
                .setDescription("Description_" + i);

            userDetailRepository.save(ud);
        }

        return user;
    }

    @GetMapping
    public User selectByLoginId(@RequestParam String profileId) {
        return queryFactory.selectFrom(user)
            .where(user.profileId.equalsIgnoreCase(profileId))
            .fetchOne();
    }

    @GetMapping(value = "/search")
    public QueryResults<User> search(@RequestParam(value = "profileId", required = false) String profileId,
                                     @RequestParam(value = "name", required = false) String name) {

        // BooleanBuilder를 이용한 방법
        QueryResults<User> listByBuilder = searchByBooleanBuilder(profileId, name);

        // BooleanExpression을 이용한 방식
        QueryResults<User> listByExpression = searchByBooleanExpression(profileId, name);

        return listByBuilder;
    }

    /**
     * BooleanBuilder를 이용한 방법
     *
     * @param profileId
     * @param name
     * @return
     */
    private QueryResults<User> searchByBooleanBuilder(String profileId, String name) {
        BooleanBuilder builder = new BooleanBuilder();
        if (!StringUtils.isEmpty(profileId)) {
            builder.and(user.profileId.containsIgnoreCase(profileId));
        }

        if (!StringUtils.isEmpty(name)) {
            builder.and(userDetail.name.containsIgnoreCase(name));
        }

        return queryFactory.selectFrom(user)
            .innerJoin(user.userDetail, userDetail)
//            .offset(1)
//            .limit(20)
            .where(builder)
            .fetchResults();
    }

    /**
     * BooleanExpression을 이용한 방식
     *
     * @param profileId
     * @param name
     * @return
     */
    private QueryResults<User> searchByBooleanExpression(String profileId, String name) {
        return queryFactory.selectFrom(user)
            .innerJoin(user.userDetail, userDetail)
//            .offset(1)
//            .limit(20)
            .where(
                eqProfileId(profileId),
                eqName(name)
            )
            .fetchResults();
    }

    private BooleanExpression eqProfileId(String profileId) {
        if (StringUtils.isEmpty(profileId)) {
            return null;
        }

        return user.profileId.eq(profileId);
    }

    private BooleanExpression eqName(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }

        return userDetail.name.eq(name);
    }
}

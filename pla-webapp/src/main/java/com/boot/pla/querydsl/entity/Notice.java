package com.boot.pla.querydsl.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import static com.boot.pla.querydsl.JpaConstants.STRATEGY_UUID2;

@Slf4j
@Data
@Accessors(chain = true)
@Entity
@Table(name = "TBM_NOTICE")
public class Notice {

    @Id
    @GeneratedValue(generator = STRATEGY_UUID2)
    @GenericGenerator(name = STRATEGY_UUID2, strategy = STRATEGY_UUID2)
    private String id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 200)
    private String subTitle;

    @Column(length = 40)
    private String category;

    @Column(length = 2000)
    private String contents;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date updatedAt;
}

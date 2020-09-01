package com.boot.pla.querydsl.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import com.boot.pla.querydsl.UserStatus;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import static com.boot.pla.querydsl.JpaConstants.STRATEGY_UUID2;

@Slf4j
@Data
@Accessors(chain = true)
@Entity
@Table(name = "TBM_USER", indexes = {
    @Index(name = "ux_profileId", columnList = "profileId", unique = true),
    @Index(name = "ix_status", columnList = "status")
})
public class User {

    @Id
    @GeneratedValue(generator = STRATEGY_UUID2)
    @GenericGenerator(name = STRATEGY_UUID2, strategy = STRATEGY_UUID2)
    private String id;

    @Column(length = 10, nullable = false)
    private String profileId;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(20) default 'NORMAL'")
    private UserStatus status;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date updatedAt;

    @OneToOne(mappedBy = "user")
    private UserDetail userDetail;

    @PrePersist
    private void prePersist() {
        log.debug("Execute PrePersist!");
    }

    @PreUpdate
    private void preUpdate() {
        log.debug("Execute PreUpdate");
    }

    @PreRemove
    private void preRemove() {
        log.debug("Execute PreRemove!");
    }
}

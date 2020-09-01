package com.boot.pla.querydsl.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@ToString(exclude = "user")
@Entity
@Accessors(chain = true)
@Table(name = "TBD_USER", indexes = {
    @Index(name = "ix_name", columnList = "name")
})
public class UserDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "master_id")
    private User user;

    @Column(length = 10, nullable = false)
    private String name;

    @Column(length = 30)
    private String email;

    @Column(length = 300)
    private String address;

    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Lob
    private String description;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date updatedAt;

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

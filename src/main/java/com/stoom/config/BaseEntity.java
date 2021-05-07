package com.stoom.config;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode
public class BaseEntity<ID> implements Serializable {

    private static final long serialVersionUID = 1635784288495940108L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ID id;

}

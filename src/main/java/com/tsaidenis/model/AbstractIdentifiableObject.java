package com.tsaidenis.model;

import jakarta.persistence.*;
import lombok.Data;

@MappedSuperclass
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class AbstractIdentifiableObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
}

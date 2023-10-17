package com.example.lecturejpa.dao;

import com.example.lecturejpa.entity.Address;

import java.util.Collection;
import java.util.Optional;

public interface AddressDao {
    Address persist(Address address);

    Optional<Address> findById(Long id);

    Collection<Address> findAll();

    void update(Address address);

    void remove(Long id);
}

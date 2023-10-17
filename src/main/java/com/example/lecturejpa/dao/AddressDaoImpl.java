package com.example.lecturejpa.dao;

import com.example.lecturejpa.entity.Address;
import com.example.lecturejpa.exceptions.AddressNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public class AddressDaoImpl implements AddressDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Address persist(Address address) {
        entityManager.persist(address);
        return address;
    }

    @Override
    public Optional<Address> findById(Long id) {
        Address foundAddress = entityManager.find(Address.class, id);
        return Optional.ofNullable(foundAddress);
    }

    @Override
    public Collection<Address> findAll() {
        return entityManager.createQuery("select a from Address a", Address.class)
                .getResultList();
    }

    @Override
    public void update(Address address) {
        entityManager.merge(address);
    }

    @Override
    public void remove(Long id) {
        Address foundAddress = entityManager.find(Address.class, id);
        if (foundAddress == null) {
            throw new AddressNotFoundException("Address with ID " + id + " not found");
        }
        entityManager.remove(foundAddress);
    }
}

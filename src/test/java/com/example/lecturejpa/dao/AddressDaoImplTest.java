package com.example.lecturejpa.dao;

import com.example.lecturejpa.dao.AddressDao;
import com.example.lecturejpa.entity.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class AddressDaoImplTest {

    @Autowired
    private AddressDao addressDao;

    @BeforeEach
    void setUp() {

        Address address1 = new Address("123 Main St", "City 1", "12345");
        Address address2 = new Address("456 Elm St", "City 2", "67890");

        addressDao.persist(address1);
        addressDao.persist(address2);
    }

    @Test
    void testPersist() {
        Address newAddress = new Address("789 Oak St", "City 3", "54321");
        Address savedAddress = addressDao.persist(newAddress);
        assertNotNull(savedAddress.getId());
    }

    @Test
    void testFindById() {
        Long id = 1L;
        Optional<Address> optionalAddress = addressDao.findById(id);
        assertTrue(optionalAddress.isPresent());
        Address foundAddress = optionalAddress.get();
        assertEquals("123 Main St", foundAddress.getStreet());
        assertEquals("City 1", foundAddress.getCity());
        assertEquals("12345", foundAddress.getZipCode());
    }

    @Test
    void testFindAll() {
        Collection<Address> addresses = addressDao.findAll();
        assertEquals(2, addresses.size());
    }
}

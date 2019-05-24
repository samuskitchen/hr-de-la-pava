package com.kometsales.sales.services;

import com.kometsales.sales.model.Seller;
import com.kometsales.sales.repository.SelleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class SellerServiceTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SelleRepository selleRepository;

    @Test
    public void getAllSeller() throws Exception {
        //given
        Seller firstSeller = new Seller();
        firstSeller.setName("Daniel De La Pava Suarez");
        firstSeller.setEmail("daniel.samkit@gmail.com");
        firstSeller.setCommissionPercentage(3.0f);
        firstSeller.setAvatar("");
        firstSeller.setCurrentCommissions(new BigDecimal(30000000));

        entityManager.persist(firstSeller);
        entityManager.flush();

        Seller secondSeller = new Seller();
        secondSeller.setName("Mario Bros");
        secondSeller.setEmail("mario.bros@nintendo.com");
        secondSeller.setCommissionPercentage(2.1f);
        secondSeller.setAvatar("");
        secondSeller.setCurrentCommissions(new BigDecimal(100000000));

        entityManager.persist(secondSeller);
        entityManager.flush();

        //when
        List<Seller> sellerList = selleRepository.findAll();

        //then
        assertThat(sellerList.size()).isEqualTo(6);
        assertThat(sellerList.get(4)).isEqualTo(firstSeller);
        assertThat(sellerList.get(5)).isEqualTo(secondSeller);


    }

    @Test
    public void getSellerById() throws Exception {
        //given
        Seller seller = new Seller();
        seller.setName("Daniel De La Pava Suarez");
        seller.setEmail("daniel.samkit@gmail.com");
        seller.setCommissionPercentage(3.0f);
        seller.setAvatar("");
        seller.setCurrentCommissions(new BigDecimal(30000000));

        entityManager.persist(seller);
        entityManager.flush();

        //when
        Optional<Seller> testSeller = selleRepository.findById(seller.getId());

        //then
        assertThat(testSeller.get().getName()).isEqualTo(seller.getName());

    }
}
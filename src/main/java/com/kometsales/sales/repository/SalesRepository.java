package com.kometsales.sales.repository;

import com.kometsales.sales.model.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Long> {

    @Query(value = "SELECT sum(rode) as rode FROM sales WHERE id_seller = :idSeller", nativeQuery = true)
    BigDecimal getTotalRodeBySeller(@Param("idSeller") Long idSeller);

}

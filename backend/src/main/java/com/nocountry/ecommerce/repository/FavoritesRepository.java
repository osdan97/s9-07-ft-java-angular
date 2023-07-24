package com.nocountry.ecommerce.repository;


import com.nocountry.ecommerce.model.Favorites;
import com.nocountry.ecommerce.model.ShippingDetailsCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface FavoritesRepository extends JpaRepository<Favorites,String> {
    List<Favorites> findAllByCustomersAccountUuid(String customerId);
    //Boolean existByCustomersAccountUuidAndProductProductUuid(String customerId, String productId);
    @Modifying
    @Query(
            value = "DELETE FROM FAVORITES s where s.account_uuid=:account_uuid AND s.product_uuid =:product_uuid",
            nativeQuery = true
    )
    void deleteFavoritesByCustomersAndProduct(@Param("account_uuid") String account_uuid,@Param("product_uuid") String product_uuid);
}

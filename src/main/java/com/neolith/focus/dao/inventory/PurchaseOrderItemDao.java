package com.neolith.focus.dao.inventory;

import com.neolith.focus.model.inventory.purchaseOrder.PurchaseOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PurchaseOrderItemDao extends JpaRepository<PurchaseOrderItem, Integer> {


    @Modifying
    @Transactional
    @Query("select poi from PurchaseOrderItem poi left join fetch poi.purchaseOrder po  where poi.asset.id = :partId and po.id = :poId")
    List<PurchaseOrderItem> findOpenPOItemListByAsset(@Param("partId") Integer partId, @Param("poId") Integer poId);
}

package com.codex.ecam.dao.inventory;

import org.springframework.stereotype.Repository;

import com.codex.ecam.model.inventory.receiptOrder.ReceiptOrder;
import com.codex.ecam.repository.FocusDataTableRepository;

@Repository
public interface ReceiptOrderDao extends FocusDataTableRepository<ReceiptOrder, Integer> {

}

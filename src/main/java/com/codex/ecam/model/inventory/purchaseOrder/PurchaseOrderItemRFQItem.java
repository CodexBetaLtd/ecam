package com.codex.ecam.model.inventory.purchaseOrder;

import javax.persistence.*;

import org.hibernate.envers.Audited;

import com.codex.ecam.model.BaseModel;
import com.codex.ecam.model.inventory.rfq.RFQItem;
import static org.hibernate.envers.RelationTargetAuditMode.NOT_AUDITED;

@Audited
@Entity
@Table(name = "tbl_purchase_order_item_rfq_item")
public class PurchaseOrderItemRFQItem extends BaseModel {

	private static final long serialVersionUID = 3882782337793662413L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "purchase_order_item_rfq_item_s")
	@SequenceGenerator(name = "purchase_order_item_rfq_item_s", sequenceName = "purchase_order_item_rfq_item_s", allocationSize = 1)
	@Column(name = "id")
	private Integer id;

	@JoinColumn(name = "purchase_order_item_id")
	@ManyToOne(targetEntity = PurchaseOrderItem.class, fetch = FetchType.LAZY)
	@Audited(targetAuditMode = NOT_AUDITED)
	private PurchaseOrderItem purchaseOrderItem;

	@JoinColumn(name = "rfq_item_id")
	@ManyToOne(targetEntity = RFQItem.class, fetch = FetchType.LAZY)
	private RFQItem rfqItem;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PurchaseOrderItem getPurchaseOrderItem() {
		return purchaseOrderItem;
	}

	public void setPurchaseOrderItem(PurchaseOrderItem purchaseOrderItem) {
		this.purchaseOrderItem = purchaseOrderItem;
	}

	public RFQItem getRfqItem() {
		return rfqItem;
	}

	public void setRfqItem(RFQItem rfqItem) {
		this.rfqItem = rfqItem;
	}

}

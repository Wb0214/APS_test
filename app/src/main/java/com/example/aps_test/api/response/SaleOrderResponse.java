package com.example.aps_test.api.response;

import java.util.List;

public class SaleOrderResponse {
    String item_id,created_at,updated_at;

    public String getItem_id() {
        return item_id;
    }
    public String getCreated_at() {
        return created_at;
    }
    public String getUpdated_at() {
        return updated_at;
    }

    public List<Sale_order> sale_order;
    public class Sale_order {
        public String item;
        public String customer_name;
        public String qty;
        public String person_id;
    }
    public String getItem() {
        return sale_order.get(0).item;
    }
    public String getCustomer_name() {
        return sale_order.get(0).customer_name;
    }
    public String getQty() {
        return sale_order.get(0).qty;
    }
    public String getPerson_id() {
        return sale_order.get(0).person_id;
    }

    public List<Parent_parts> parent_parts;
    public class Parent_parts {
        public List<Downstream_child> downstream_child;
    }
    public class Downstream_child {
        public String unit_qty,nuse_qty,remark;
        public Parent parent;
    }
    public String getRemark() {
        return parent_parts.get(0).downstream_child.get(0).remark;
    }

    public String getUnitQty() {
        return parent_parts.get(0).downstream_child.get(0).unit_qty;
    }

    public String getNuseQty() {
        return parent_parts.get(0).downstream_child.get(0).nuse_qty;
    }

    public int getSize() {
        return parent_parts.get(0).downstream_child.size();
    }

    public class Parent {
        public String material_id, bomkey_name, qty, unit_id, updated_at;
    }
    public String getMaterialId() {
        return parent_parts.get(0).downstream_child.get(0).parent.material_id;
    }
    public String getBomkeyName() {
        return parent_parts.get(0).downstream_child.get(0).parent.bomkey_name;
    }
    public String getParentQty() {
        return parent_parts.get(0).downstream_child.get(0).parent.qty;
    }
    public String getUnitId() {
        return parent_parts.get(0).downstream_child.get(0).parent.unit_id;
    }
    public String getUpdatedAt() {
        return parent_parts.get(0).downstream_child.get(0).parent.updated_at;
    }
}

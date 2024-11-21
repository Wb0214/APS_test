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
}

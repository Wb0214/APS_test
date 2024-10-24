package com.example.aps_test.api.response;

public class ROMResponse {
    public String unit_id,unit_qty,base_qty;

    public String UnitId() {
        return unit_id; //PCS
    }
    public String UnitQty() {
        return unit_qty;
    }
    public String BaseQty() {
        return base_qty;
    }

    public Parent parent;
    public class Parent {
        public String bomkey_name;
        public String bomkey_id;

    }
    public String BomkeyName() {
        return parent.bomkey_name;
    }
    public String BomkeyId() {
        return parent.bomkey_id;
    }
}

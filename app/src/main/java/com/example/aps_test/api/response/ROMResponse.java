package com.example.aps_test.api.response;

public class ROMResponse {
    public String unit_id,unit_qty,nuse_qty,base_qty,created_at,updated_at,down_id;

    public String UnitId() {
        return unit_id; //PCS
    }
    public String UnitQty() {
        return unit_qty;
    }
    public String NuseQty() {
        return nuse_qty;
    }
    public String BaseQty() {
        return base_qty;
    }
    public String CreatedAt() {
        return created_at;
    }
    public String UpdatedAt() {
        return updated_at;
    }
    public String DownId() {
        return down_id;
    }

    public Parent parent;
    public class Parent {
        public String bomkey_name;
        public String bomkey_id;
        public String material_id;
    }
    public String BomkeyName() {
        return parent.bomkey_name;
    }
    public String BomkeyId() {
        return parent.bomkey_id;
    }
    public String MaterialId() {
        return parent.material_id;
    }
}

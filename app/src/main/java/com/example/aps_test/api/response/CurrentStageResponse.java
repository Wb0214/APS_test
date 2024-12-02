package com.example.aps_test.api.response;

import java.util.List;

public class CurrentStageResponse {
    String mo_id,item_id,item_name,online_date,
            complete_date,qty,so_id,created_at,updated_at;

    public String MoId() {
        return mo_id;
    }
    public String ItemId() {
        return item_id;
    }
    public String ItemName() {
        return item_name;
    }
    public String OnlineDate() {
        return online_date;
    }
    public String CompleteDate() {
        return complete_date;
    }
    public String Qty() {
        return qty;
    }
    public String SoId() {
        return so_id;
    }
    public String CreatedAt() {
        return created_at;
    }
    public String UpdatedAt() {
        return updated_at;
    }

    public int getSize() {
        return related_parent_part.downstream_child.size();
    }

    public Related_tech_route related_tech_route;
    public class Related_tech_route {
        public String tech_routing_name;
    }
    public String TechRouteName() {
        return related_tech_route.tech_routing_name;
    }

    public Related_parent_part related_parent_part;
    public class Related_parent_part{
        public List<Downstream_child> downstream_child;
    }

    public class Downstream_child {
        public Parent parent;
        public String unit_id,unit_qty,nuse_qty;
    }
    public String getUnitId() {
        return related_parent_part.downstream_child.get(0).unit_id;
    }
    public String getUnitQty() {
        return related_parent_part.downstream_child.get(0).unit_qty;
    }
    public String getNuseQty() {
        return related_parent_part.downstream_child.get(0).nuse_qty;
    }
    public class Parent{
        public String bomkey_name, material_id;
    }
    public String getMaterialId() {
        return related_parent_part.downstream_child.get(0).parent.material_id;
    }
    public String getBomKeyName() {
        return related_parent_part.downstream_child.get(0).parent.bomkey_name;
    }
}

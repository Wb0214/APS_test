package com.example.aps_test.api.response;

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

    public Related_tech_route related_tech_route;
    public class Related_tech_route {
        public String tech_routing_name;
    }
    public String TechRouteName() {
        return related_tech_route.tech_routing_name;
    }
}

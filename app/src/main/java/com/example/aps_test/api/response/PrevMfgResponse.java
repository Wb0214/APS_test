package com.example.aps_test.api.response;

public class PrevMfgResponse {
    public String mo_id,so_id,
                item_id,item_name,
                online_date,qty,complete_date;

    public String MoId() {
        return mo_id;
    }
    public String SoId() {
        return so_id;
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
    public String Qty() {
        return qty;
    }
    public String CompleteDate() {
        return complete_date;
    }

    public MoResponse.Related_tech_route related_tech_route;
    public class Related_tech_route {
        public String tech_routing_name;
    }
    public String TechRoutingName() {
        return related_tech_route.tech_routing_name;
    }
    //////////////////////////////////////////////////////////////////////


}

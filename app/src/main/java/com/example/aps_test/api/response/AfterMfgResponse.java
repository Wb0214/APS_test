package com.example.aps_test.api.response;

import java.util.List;

public class AfterMfgResponse {
    String item_id,created_at,updated_at,nuse_qty;

    public String ItemId() {
        return item_id;
    }
    public String CreatedAt() {
        return created_at;
    }
    public String UpdatedAt() {
        return updated_at;
    }
    public String NuseQty() {
        return nuse_qty;
    }

    public Related_top_parent related_top_parent;
    public class Related_top_parent {
        public String bomkey_name;
        public String unit_id;
        public List<Manufactures> manufactures;
    }
    public String BomkeyName() {
        return related_top_parent.bomkey_name;
    }
    public String UnitId() {
        return related_top_parent.unit_id;
    }

    public class Manufactures {
        public String mo_id;
        public String item_name;
        public String online_date;
        public String complete_date;
        public String qty;
    }
    public String MoId() {
        return related_top_parent.manufactures.get(0).mo_id;
    }
    public String ItemName() {
        return related_top_parent.manufactures.get(0).item_name;
    }
    public String OnlineDate() {
        return related_top_parent.manufactures.get(0).online_date;
    }
    public String CompleteDate() {
        return related_top_parent.manufactures.get(0).complete_date;
    }
    public String Qty() {
        return related_top_parent.manufactures.get(0).qty;
    }
}

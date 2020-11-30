package hfad.com.liquirstore;

public class LiquirBatch {

    int bottle_batch_id;
    String name;
    String description;
    String creator_permit;
    String creator_name;
    String current_owner_permit;
    String current_owner_name;
    int volume;
    String product_id;

    public LiquirBatch(int bottle_batch_id, String name, String description, String creator_permit, String creator_name, String current_owner_permit, String current_owner_name, int volume, String product_id) {
        this.bottle_batch_id = bottle_batch_id;
        this.name = name;
        this.description = description;
        this.creator_permit = creator_permit;
        this.creator_name = creator_name;
        this.current_owner_permit = current_owner_permit;
        this.current_owner_name = current_owner_name;
        this.volume = volume;
        this.product_id = product_id;
    }

    public int getBottle_batch_id() {
        return bottle_batch_id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCreator_permit() {
        return creator_permit;
    }

    public String getCreator_name() {
        return creator_name;
    }

    public String getCurrent_owner_permit() {
        return current_owner_permit;
    }

    public String getCurrent_owner_name() {
        return current_owner_name;
    }

    public int getVolume() {
        return volume;
    }

    public String getProduct_id() {
        return product_id;
    }
}

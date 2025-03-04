package cn.demo.model;

public class TransactionLog {
    private String id;

    private String business;

    private String foreignKey;

    public TransactionLog(String id, String business, String foreignKey) {
        this.id = id;
        this.business = business;
        this.foreignKey = foreignKey;
    }

    public TransactionLog() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business == null ? null : business.trim();
    }

    public String getForeignKey() {
        return foreignKey;
    }

    public void setForeignKey(String foreignKey) {
        this.foreignKey = foreignKey == null ? null : foreignKey.trim();
    }
}
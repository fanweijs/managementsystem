package com.company.model;

/**
 * @author  樊委
 * 权限项实体
 *
 */
public class PermissionItem {
    private String account;
    private Integer itemId;

    public PermissionItem(String account, Integer itemId) {
        this.account = account;
        this.itemId = itemId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    @Override
    public String toString() {
        return "PermissionItem{" +
                "account='" + account + '\'' +
                ", itemId=" + itemId +
                '}';
    }
}

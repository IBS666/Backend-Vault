package com.example.Vault.dto;

import com.example.Vault.model.VaultItem;
import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class VaultItemWithDataResponse {
    private Long itemId;
    private String itemTitle;
    private VaultItem.ItemType itemType;
    private Object itemData; // Données déchiffrées selon le type
    private String createdAt;
    private String updatedAt;

    public VaultItemWithDataResponse(VaultItem item) {
        this.itemId = item.getItemId();
        this.itemTitle = item.getItemTitle();
        this.itemType = item.getItemType();
        this.createdAt = item.getCreatedAt().toString();
        this.updatedAt = item.getUpdatedAt().toString();

        // Convertir itemData selon le type
        switch (item.getItemType()) {
            case LOGIN:
                this.itemData = item.getItemDataObject(LoginData.class);
                break;
            case CARD:
                this.itemData = item.getItemDataObject(CardData.class);
                break;
            case NOTE:
                this.itemData = item.getItemDataObject(NoteData.class);
                break;
            case IDENTITY:
                this.itemData = item.getItemDataObject(IdentityData.class);
                break;
            default:
                this.itemData = null;
        }
    }
}
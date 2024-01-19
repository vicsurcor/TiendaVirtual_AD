package es.masanz.tiendaVirtual.dto;

import java.util.Date;

public class CompraDTO {

    int objectId;
    int buyingUser;
    int sellingUser;
    Date sellingDate;

    public CompraDTO(int objectId, int buyingUser, int sellingUser, Date sellingDate){

        this.objectId = objectId;
        this.buyingUser = buyingUser;
        this.sellingUser = sellingUser;
        this.sellingDate = sellingDate;

    }

    public int getObjectId() {
        return objectId;
    }

    public int getBuyingUser() {
        return buyingUser;
    }

    public int getSellingUser() {
        return sellingUser;
    }

    public void setSellingDate(Date sellingDate) {
        this.sellingDate = sellingDate;
    }

    public Date getSellingDate() {
        return sellingDate;
    }

}

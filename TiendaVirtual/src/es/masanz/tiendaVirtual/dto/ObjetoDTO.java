package es.masanz.tiendaVirtual.dto;

public class ObjetoDTO {

    String name;
    Double price;
    int userId;

    public ObjetoDTO(String name, Double price, int userId){

        this.name = name;
        this.price = price;
        this.userId = userId;

    }


    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}

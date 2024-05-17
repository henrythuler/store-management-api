package br.com.thuler.store.model.enums;

import br.com.thuler.store.exceptions.NotFoundException;

public enum OrderStatus {
    WAITING_PAYMENT(0),
    PAID(1),
    SHIPPED(2),
    DELIVERED(3),
    CANCELED(4);

    private int code;

    private OrderStatus(int code){
        this.code = code;
    }

    public int getCode(){
        return this.code;
    }

    public static OrderStatus valueOf(int code){
        for(OrderStatus status : OrderStatus.values()){
            if(status.code == code){
                return status;
            }
        }
        throw new NotFoundException("Invalid order status code...");
    }

}

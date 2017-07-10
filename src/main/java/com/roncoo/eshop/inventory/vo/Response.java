package com.roncoo.eshop.inventory.vo;

/**
 * Component:
 * Description:
 * Date: 17/7/7
 *
 * @author yue.zhang
 */
public class Response {

    public final static String SUCCESS = "success";

    public final static String FAILURE = "failure";

    private String status;

    private String message;

    public Response(){

    }

    public Response(String status) {
        this.status = status;
    }

    public Response(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}

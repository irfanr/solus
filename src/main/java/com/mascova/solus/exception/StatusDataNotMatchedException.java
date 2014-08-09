/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mascova.solus.exception;

/**
 *
 * @author irfan
 */
public class StatusDataNotMatchedException extends MilesException {

    private String message = "Status Data Pelatihan tidak sama dengan status Peserta";

    public StatusDataNotMatchedException() {
        super();
    }

    public StatusDataNotMatchedException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

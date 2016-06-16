package com.movil.sony.activitylog.httpConection;

/**
 * Created by SONY on 16/06/2016.
 */
public class HttpResponse {

    public static final int NO_ERROR_=0;
    public static final int ERROR_TIMEOUT =1;
    public static final int ERROR_404 =2;
    public static final int ERROR=3;

    String msj;
    int status;
    int error;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getMsj() {
        return msj;
    }

    public void setMsj(String msj) {
        this.msj = msj;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

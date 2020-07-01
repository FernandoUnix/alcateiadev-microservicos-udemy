package com.br.alcateiadev.padroesapi.padroesapi.exceptios;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FeignExceptionDecode extends RuntimeException {
    private final int status;

    public FeignExceptionDecode(int status, String msg) {
        super(msg);
        this.status = status;
    }

    public String getMessageLocal() {
        if (getMessage() == null)
            return "";

        return getMessage().toLowerCase().replaceAll(" ", "_");
    }
}

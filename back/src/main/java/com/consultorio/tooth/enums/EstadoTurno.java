
package com.consultorio.tooth.enums;


public enum EstadoTurno {
    
    PROGRAMADO,
    CONFIRMADO,
    CANCELADO;
    
    public static EstadoTurno getPROGRAMADO() {    
        return PROGRAMADO;
    }

    public static EstadoTurno getCONFIRMADO() {
        return CONFIRMADO;
    }

    public static EstadoTurno getCANCELADO() {
        return CANCELADO;
    }
}

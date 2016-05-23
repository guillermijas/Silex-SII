package entrega1;


public class Enumeraciones{
    
    private Long idAviso = 0L;
    private Long idOt = 0L;

    public Long getIdAviso() {
        return idAviso;
    }

    public void incrIdAviso() {
        idAviso++;
    }

    public Long getIdOt() {
        return idOt;
    }

    public void incrIdOt() {
        idOt++;
    }

    public void setIdAviso(Long idAviso) {
        this.idAviso = idAviso;
    }

    public void setIdOt(Long idOt) {
        this.idOt = idOt;
    }
}

/*
public enum prioridad {
        BAJA, MEDIA, ALTA
    }

    public enum estado {
        INCIDENCIA, NUEVA, EN_PROCESO, CERRADA
    }

    public enum gravedad {
        LEVE, MEDIA, ALTA
    }

    public enum Rol {
        CLIENTE, CALL_CENTER, SUPERVISOR, OPERARIO, ADMIN;
    }
*/
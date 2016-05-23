package entrega1;

public class Enumeraciones {

    public enum prioridad {
        BAJA, MEDIA, ALTA
    }

    public enum estado {
        INCIDENCIA, NUEVO, EN_PROCESO, CERRADO
    }

    public enum gravedad {
        LEVE, MEDIA, ALTA
    }

    public enum Rol {
        CLIENTE, CALL_CENTER, SUPERVISOR, OPERARIO, ADMINISTRADOR;
    }
}

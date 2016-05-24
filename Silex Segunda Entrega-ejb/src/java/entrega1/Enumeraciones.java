package entrega1;

public class Enumeraciones {

    public enum prioridad {
        BAJA, MEDIA, ALTA // Baja -> 0, Media -> 1 y Alta -> Alta
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

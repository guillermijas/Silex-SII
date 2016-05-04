package entrega1;

public class Enumeraciones {
    private static Long idAviso = 0L;
    private static Long idOt = 0L;
    
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
    
    public static Long getIdAviso(){
        return idAviso;
    }
    
    public static void incrIdAviso(){
        idAviso++;
    }
    
    public static Long getIdOt(){
        return idOt;
    }
    
    public static void incrIdOt(){
        idOt++;
    }
}

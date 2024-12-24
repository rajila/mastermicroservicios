package lib.rdajila.helper;

import java.util.List;

public final class ConstantsHelper {
    public static final String SUCCESS = "SUCCESS";
    public static final String FAILURE = "FAILURE";

    public static final String SOURCE_USUARIO_TYPE = "U";
    public static final String SOURCE_PELICULA_TYPE = "P";
    public static final String DOCUMENT_GEN_TYPE = "Portada";

    public static final List<String> SOURCE_TYPES = List.of(
    		SOURCE_USUARIO_TYPE.toLowerCase(), 
    		SOURCE_PELICULA_TYPE.toLowerCase());
    
    public static final List<String> DOCUMENT_TYPES = List.of(
    		DOCUMENT_GEN_TYPE.toLowerCase());
}

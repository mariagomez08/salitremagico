package com.sofka.salitremagico.constants;

public final class CustomMessages {

    // Constructor privado para evitar instanciación
    private CustomMessages() {
        throw new UnsupportedOperationException("Esta es una clase de constantes y no debe instanciarse.");
    }

    // Mensajes de error
    public static final String CLIENTE_NO_ENCONTRADO = "El cliente solicitado no existe en el sistema.";
    public static final String EMPLEADO_NO_ENCONTRADO = "El empleado solicitado no existe en el sistema.";
    public static final String ATRACCION_NO_ENCONTRADA = "La atracción solicitada no está disponible.";
    public static final String TIQUETE_NO_VALIDO = "El tiquete no es válido o no está asociado a un cliente.";
    public static final String ESTATURA_INSUFICIENTE = "El cliente no cumple con la estatura mínima para esta atracción.";
    public static final String ACCESO_DENEGADO = "El acceso a esta función está restringido.";
    public static final String DATOS_INVALIDOS = "Los datos proporcionados no son válidos.";
    public static final String ERROR_INTERNO = "Ha ocurrido un error interno. Por favor, intente más tarde.";
  

    // Mensajes informativos o de éxito
    public static final String CLIENTE_REGISTRADO = "Cliente registrado exitosamente.";
    public static final String EMPLEADO_REGISTRADO = "Empleado registrado exitosamente.";
    public static final String ATRACCION_ACTUALIZADA = "La atracción se actualizó correctamente.";
}
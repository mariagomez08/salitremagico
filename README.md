# 🎢 Sistema de Gestión - Parque Salitre Mágico 🎠

Este proyecto es una aplicación para gestionar las operaciones de un parque de diversiones, como la administración de clientes, empleados, atracciones, y estadísticas de uso. Está desarrollado utilizando **Spring Boot**, **Thymeleaf**, **PostgreSQL**, y otras tecnologías

## Funcionalidades Principales
1. **Registro de clientes**:
   - Permite registrar clientes con información personal.
   - Valida si el cliente es menor de edad.
2. **Gestión de empleados**:
   - Registro de roles como operadores, mantenimiento, etc.
   - Modificación y asignación de horarios.
3. **Gestión de atracciones**:
   - Control del estado (disponible, inhabilitada, mantenimiento).
   - Registro de descripción, clasificación, las ondiciones de uso, Estado, Estatura minima y Frecuencia en las atracciones.
4. **Validación de acceso a atracciones**:
   - Verificación de la estatura mínima para la seguridad de los usuarios.
5. **Estadísticas**:
   - Visualizacion de fecuencia de visita en las atracciones.
   - Visualización de ocupación del parque.
6. **Promociones**:
   - Identificación de clientes frecuentes.

---

## Tecnologías Usadas
- **Backend**:
  - Java con Spring Boot (MVC)
  - Hibernate para manejo de JPA.
  - PostgreSQL como base de datos.
- **Frontend**:
  - Thymeleaf para renderización de vistas.
- **Otras Herramientas**:
  - Maven para gestión de dependencias.
  - Git y GitHub para control de versiones.

---

## Configuración y Ejecución del Proyecto

### **Prerrequisitos**
1. **Java**: JDK 11 o superior.
2. **Maven**: Instalado y configurado en tu sistema.
3. **PostgreSQL**: Base de datos instalada.

### **Configuración**
1. **Clona este repositorio**:
   ```bash
   git clone https://github.com/mariagomez08/salitremagico.git
   cd salitremagico



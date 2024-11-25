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
## Arquitectura del proyecto

La arquitectura utilizada será MVC (Modelo-Vista-Controlador) la cual se utiliza para estructurar la capa de presentación y facilitar la interacción entre los componentes de la aplicación web, respetando la separación de responsabilidades.

![image](https://github.com/user-attachments/assets/a7430e7b-5109-42c9-85d9-d2dd16220e97)

---

## Tecnologías Usadas
- **Backend**:
  - Java con Spring Boot (MVC)
  - Hibernate para manejo de JPA.
  - PostgreSQL como base de datos.
- **Frontend**:
  - Thymeleaf para renderización de vistas.
  - Bootstrap para diseño responsivo.
- **Otras Herramientas**:
  - Maven para gestión de dependencias.
  - Git y GitHub para control de versiones.
  - Project IDX para el entorno de desarrollo del projecto.

---

## **Requisitos Previos**
Antes de iniciar, asegúrate de tener instalado:
- **Java Development Kit (JDK)** 17 o superior.
- **Apache Maven** para la gestión de dependencias.
- **PostgreSQL** como base de datos relacional.
- **Git** para clonar el repositorio.
- Un **IDE** como Project IDX
- 
## **Instalación**

### **Configuración**
1. **Clona este repositorio**:
   ```bash
   git clone https://github.com/mariagomez08/salitremagico.git
   cd salitremagico

2. **Instala las Dependencias Usa Maven para instalar las dependencias:**
   ```bash
   mvn clean install

**Ejecución**

#### **Desde el IDE**
1. Abre el proyecto en tu IDE, preferiblemente IntelliJ IDEA o Eclipse.
2. Ejecuta la clase principal: `SalitreMagicoApplication.java`.

#### **Desde la Línea de Comandos**
1. Ve al directorio raíz del proyecto y ejecuta la aplicacion
```bash
   cd salitremagico
   mvn spring-boot:run

# Instalación/Ejecución para ProyectoJuego (libGDX)

## Descripción
Este proyecto corresponde a un juego desarrollado en **Java 11** utilizando el framework **libGDX**, ejecutable desde el entorno **Eclipse IDE**.
 A continuación, se presentan dos alternativas de importar y ejecutar el proyecto.



## Requisitos previos
Antes de comenzar, asegúrate de tener instalado lo siguiente:
- **Java JDK 11**
  Verifica que Eclipse esté configurado para usar esta versión.  
  (Menú: `Window → Preferences → Java → Installed JREs`)


- **Eclipse IDE for Java Developers**
 Recomendado: versión con soporte para Gradle o que tenga el plugin **Buildship Gradle Integration** instalado.
 (Para verificarlo: en `Eclipse → Help → Eclipse Marketplace → busca “Buildship Gradle Integration”`)

---

## OPCIÓN 1: Importar directamente desde GitHub
Sigue estos pasos para importar el proyecto usando su repositorio en GitHub:
### 1. Abrir Eclipse
Ejecuta Eclipse y selecciona tu workspace (la carpeta donde guardarás los proyectos).
### 2. Importar el proyecto
En el menú superior:
`File → Import...`

Luego selecciona:
`Git → Projects from Git (with smart import)`

y haz clic en `Next`.
### 3. Clonar desde GitHub
Selecciona la opción:
`Clone URI`

y presiona `Next`.
En el campo URI, pega el siguiente enlace del repositorio:
`https://github.com/MarNara/ProyectoJuego.git`

Presiona `Next` varias veces para aceptar las opciones por defecto (rama main, carpeta de clonación, etc.).
### 4. Importar como proyecto Gradle
Cuando Eclipse haya clonado el repositorio, aparecerá la opción:
`Import existing Eclipse projects` o `Import as general project / Gradle project`

Selecciona `Import as Gradle Project` y haz clic en `Finish`.
Si Eclipse pregunta si deseas importar múltiples proyectos, acepta. libGDX suele crear módulos como core, desktop, etc.

---

## OPCIÓN 2: Importar desde un archivo ZIP (usando Gradle)
Si tienes el proyecto comprimido en un archivo .zip (por ejemplo, ProyectoJuego.zip), puedes abrirlo fácilmente en Eclipse siguiendo estos pasos:

### 1. Abrir Eclipse
Abre Eclipse IDE y selecciona tu carpeta de workspace (donde se guardarán los proyectos).

### 2. Iniciar la importación
En el menú principal de Eclipse, selecciona:
`File → Import...`

Luego elige la opción:
`Gradle → Existing Gradle Project`

y haz clic en `Next`.
Esta es la opción recomendada, ya que Eclipse detectará automáticamente los módulos y configuraciones del proyecto (como core, desktop, etc.) usando Gradle.

### 3. Seleccionar el archivo ZIP
En la ventana de importación:
Haz clic en `Browse...`

Busca y selecciona el archivo `ProyectoJuego.zip`.

Eclipse mostrará la ruta del proyecto descomprimido temporalmente.

Si Eclipse no permite seleccionar directamente el .zip, primero descomprímelo manualmente en una carpeta (por ejemplo, en `Documentos/ProyectoJuego`), y luego selecciona esa carpeta como `Project root directory`.
Haz clic en `Finish`.

### 4. Esperar la configuración
Eclipse analizará el archivo `build.gradle` y descargará automáticamente las dependencias necesarias (esto puede tardar unos minutos la primera vez).
Durante este proceso, verás una barra de progreso indicando `“Importing Gradle Project”`.

---

## Verificar versión de Java
Una vez importado el proyecto:
`Click derecho sobre el proyecto → Properties → Java Compiler`

Asegúrate que el `Compiler compliance level` sea `11`.


Si no lo es, cambia a `11`, luego `Apply and Close`.

---

## ¿Cómo ejecutar el juego?
Si lograste importar con éxito el proyecto utilizando cualquiera de las dos alternativas, lo que debes hacer ahora es ejecutar el juego. Para eso debes ir a las carpetas del proyecto, especificamente la que pone `SpaceNav2024-lwjgl3`, dentro de esta busca:
`src/main/java/puppy/code/lwjgl3`

Y selecciona la clase llamada:
`Lwjgl3Launcher.java`

Haz clic derecho sobre ella en `→ Run As → Java Application`
Si todo está correcto, el juego se ejecutará en una nueva ventana.


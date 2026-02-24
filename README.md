# Garaje Premium (Myproyectoapp)

Una aplicación de Android moderna para gestionar una colección de marcas y modelos de automóviles. La aplicación está construida completamente con tecnologías de Jetpack, incluyendo Compose para la interfaz de usuario, Room para la base de datos local y Navigation para la navegación entre pantallas.

## 🚀 Características

- **Ver Marcas:** Muestra una lista de marcas de coches con un diseño limpio y moderno.
- **Añadir/Eliminar Marcas:** Añade nuevas marcas de coches a través de un diálogo o elimina las existentes.
- **Ver Modelos:** Toca una marca para ver una lista detallada de sus modelos.
- **Añadir/Eliminar Modelos:** Añade nuevos modelos a una marca, especificando nombre, año y precio.
- **Persistencia de Datos:** Toda la información se guarda localmente usando una base de datos Room.
- **Interfaz Moderna:** Diseño elegante construido con Material 3, con un fuerte enfoque en la experiencia de usuario.

## 🛠️ Stack Tecnológico y Arquitectura

- **UI:** 100% [Jetpack Compose](https://developer.android.com/jetpack/compose).
- **Diseño:** [Material 3](https://m3.material.io/).
- **Arquitectura:** MVVM (Model-View-ViewModel).
  - `ViewModel` para la lógica de negocio.
  - `LiveData` para observar cambios en los datos de forma reactiva.
- **Base de Datos:** [Room](https://developer.android.com/training/data-storage/room) para persistencia de datos local.
- **Navegación:** [Navigation for Compose](https://developer.android.com/jetpack/compose/navigation) para gestionar los flujos entre pantallas.
- **Inyección de Dependencias (implícita):** `viewModel()` de Jetpack para la creación y gestión del ViewModel.
- **Procesamiento de Anotaciones:** [KSP (Kotlin Symbol Processing)](https://kotlinlang.org/docs/ksp-overview.html) para el compilador de Room.

## 📁 Estructura del Proyecto

- `app/src/main/java/com/tuplataforma/myproyectoapp`
  - `data/`: Contiene las entidades de Room (`Marca`, `Modelo`), el DAO (`CarDao`) y la clase de la base de datos (`AppDatabase`).
  - `ui/`: Incluye las pantallas de Compose (`MarcaScreen`, `ModeloScreen`) y el `CarViewModel` que gestiona la lógica de la UI.
  - `MainActivity.kt`: El punto de entrada de la aplicación, donde se configura el `NavHost` de Compose.

## ⚙️ Cómo Compilar y Ejecutar

1.  **Clona el repositorio:**
    ```bash
    git clone <URL_DEL_REPOSITORIO>
    ```
2.  **Abre el proyecto** en Android Studio (versión Iguana o superior recomendada).
3.  **Sincroniza Gradle:** El IDE debería hacerlo automáticamente. Si no, ve a `File > Sync Project with Gradle Files`.
4.  **Ejecuta la aplicación:** Selecciona un emulador o conecta un dispositivo físico y presiona el botón de **Run** (▶️).

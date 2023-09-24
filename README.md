# Cronómetro con CRUD y Base de Datos SQLite

Este proyecto es una aplicación de cronómetro que permite realizar operaciones CRUD (Crear, Leer, Actualizar, Borrar) y almacena los registros localmente utilizando SQLite como base de datos. A continuación, se presentan las principales características y componentes del proyecto:

<img src="https://github.com/jamirou/CronosApp/assets/48457084/dae01836-2f05-4571-82d4-d19e788882f7" width="300" alt="cap1">


## Características Principales

- **Cronómetro**: La aplicación incluye una función de cronómetro que permite iniciar, pausar, detener y reiniciar el tiempo.

- **Operaciones CRUD**: Puedes realizar las siguientes operaciones CRUD en la aplicación:
  - Crear: Guardar el tiempo cronometrado con un título personalizado.
  - Leer: Ver una lista de todos los tiempos cronometrados guardados.
  - Actualizar: Editar el título de un tiempo cronometrado existente.
  - Borrar: Eliminar un tiempo cronometrado de la lista.

- **Base de Datos SQLite**: Los tiempos cronometrados se almacenan localmente utilizando SQLite como base de datos.

### Tecnologías Utilizadas

- Kotlin: Lenguaje de programación principal.
- Android Jetpack: Conjunto de bibliotecas y herramientas recomendadas por Google para el desarrollo de aplicaciones Android.
- Room: Biblioteca para trabajar con bases de datos SQLite en Android.
- Dagger Hilt: Biblioteca de inyección de dependencias para Android.
- Compose: Framework de IU moderno para crear interfaces de usuario nativas de Android.


## Componentes Principales

### CronosDatabase

```kotlin
@Database(entities = [Cronos::class], version = 1, exportSchema = false)
abstract class CronosDatabase : RoomDatabase() {
    abstract fun cronosDao(): CronosDatabaseDao
}
```

La clase CronosDatabase es la base de datos de la aplicación y define la tabla para almacenar los tiempos cronometrados.
```kotlin
@Dao
interface CronosDatabaseDao {
    @Query("SELECT * FROM cronos")
    fun getCronos(): Flow<List<Cronos>>

    // Otras consultas y operaciones CRUD...
}
```
### CronometroViewModel
```kotlin
class CronometroViewModel @Inject constructor(private val repository: CronosRepository) : ViewModel() {
    // Implementación del ViewModel para el cronómetro y las operaciones relacionadas.
}
```
CronometroViewModel es el ViewModel que maneja la lógica del cronómetro y las operaciones relacionadas, como iniciar, pausar, detener y guardar tiempos cronometrados.

### CronosViewModel
```kotlin
class CronosViewModel @Inject constructor(private val repository: CronosRepository) : ViewModel() {
    // Implementación del ViewModel para las operaciones CRUD y la gestión de la lista de tiempos cronometrados.
}
```
CronosViewModel es el ViewModel encargado de gestionar la lista de tiempos cronometrados y realizar operaciones CRUD en la base de datos.




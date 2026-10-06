# ModoGuardian – Login por roles (MVVM + Compose)

App Android (Kotlin + Jetpack Compose) con login diferenciado por rol.

## Usuarios de prueba (ficticios)
| Correo | Contraseña | Rol |
|---|---|---|
| admin@guardian.test | 123456 | Admin |
| supervisor@guardian.test | 123456 | Supervisor |
| operador@guardian.test | 123456 | Operador |

## Estructura MVVM
- `model/` – `LoginUiState`, `LoginErrores`, `Rol`, `Usuario`, `ResultadoAuth`, `SecurityEvent`
- `repository/` – `AuthRepository` (usuarios de prueba), `SecurityRepository`
- `viewmodel/` – `LoginViewModel`, `MainViewModel` (navegación con `MutableSharedFlow<NavigationEvent>`), `EstadoViewModel`
- `ui/screens/` – `LoginScreen` (Compact = Column, Medium/Expanded = Card + Row), `HomeScreen`
- `ui/utils/WindowSizeUtils.kt` – Window Size Classes
- `navigation/` – `Screen`, `NavigationEvent`
- `MainActivity` – `NavHost` + `LaunchedEffect` que colecta los eventos de navegación

## Demo de error de conectividad
En el login, activa el interruptor **"Simular sin conexión (demo)"** e inicia sesión.

#!/bin/bash

# Script para eliminar archivos de Gradle de todos los microservicios
# Ejecutar desde el directorio raíz del proyecto

echo "Limpiando archivos de Gradle..."

# Lista de directorios de microservicios (ajusta según tu estructura)
MICROSERVICES=(
    "ms-config-tarifa-duracion"
    "ms-desc-client-frecuente"
    "ms-desc-num-personas"
    "ms-rack-semanal"
    "ms-reportes"
    "ms-reserva-comprobante"
    "ms-tarifa-especial"
)

for ms in "${MICROSERVICES[@]}"; do
    if [ -d "$ms" ]; then
        echo "Limpiando $ms..."
        cd "$ms"

        # Eliminar archivos de Gradle
        rm -f build.gradle
        rm -f gradlew
        rm -f gradlew.bat
        rm -f settings.gradle
        rm -rf .gradle/
        rm -rf gradle/
        rm -rf build/

        echo "✓ $ms limpiado"
        cd ..
    else
        echo "⚠️  Directorio $ms no encontrado"
    fi
done

echo "✅ Limpieza completada"
echo "Ahora debes crear un pom.xml para cada microservicio"
# Rescate de Patitas

## Entrega IV (2021-10-01): Persistencia

### Enunciado

El enunciado para esta entrega puede verse en [este docs](https://docs.google.com/document/d/e/2PACX-1vRvFTDJ2FMiWZR7jHuhYcZatwLPLP5Xj8yEmv17ArmJbW4TNI6arvpi1X7DSmUEcAswwBBB82O0KT2V/pub)

### Requerimientos

1. Se deberán persistir las entidades del modelo planteado. Para ello se debe utilizar un ORM. 

### Entregables Requeridos

> Modelo de objetos: diagrama de clases actualizado según las modificaciones que haya sufrido por  la técnica de mapeo objeto – relacional. 

![Requerimiento 1 | 2](images/04-entrega/01-2-req.png)

![Requerimiento 1 | 1](images/04-entrega/01-1-req.png)

> Modelo de datos: diagrama de entidad relación físico

![Requerimiento 2](images/04-entrega/02-req.png)

> Implementacion

Ver [Changelog](./CHANGELOG.md) de cambios que refieran a _ORM_ y aquellas que involucren _Tests_

> Justificaciones

Notamos conveniente el uso de una clase abstracta `@MappedSupperClass PersistentEntity` la cual se encargara de lidiar con los temas relacionados a los `IDs`, también de esta manera nos ahorramos la repetición de lógica. Por otro lado, resulto conveniente tambien que los repositorios que debian recuperar informacion utilizaban practicas muy similares, a lo que surgio una nueva `@MappedSupperClass PersistentEntitySet<T>` de esta manera alineamos los repositorios a unos métodos `CRUD` (_Create, Read, Update and Delete_)  que tendrán en común, aunque no necesariamente los implementen.

Como ya poseiamos nuestras clases divididas en varios `packages`, notamos que los que séan necesarios persistir se encontraban dentro del package `model.*`, dado que el resto hace referencia a _servicios_ y _API connectors_


Para el manejo de herencias, en el caso de `Preguntas` optamos por realizar el _approach_ `SINGLE TABLE`, ya que compartian la mayoría de los campos, una de las diferencias era la lista de respuestas, las cuales fueron mapeadas como un `@ElementCollection` por lo cual ya existiria otra tabla para las mismas.

Por otro lado, para los `Usuarios`, `DuenioMascota` y `Voluntario` optamos por `@MappedSuperclass` ya que a pesar de compartir datos, optamos por que cada entidad tenga su propia tabla, por cuestiones de facilidad, y teniendo en cuenta que los voluntarios se relacionan mas con las asociaciones que con las mascotas, de momento pronosticamos que incluirlos con los `Dueños` impactaria en la _performance_.

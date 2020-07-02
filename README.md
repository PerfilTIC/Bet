# BetMasivian

Este Es un proyecto realizado en el framework de Spring-boot para la prueba tecnica de la compañia Masivian.

Acontinuación explicare cada Endpoint dado el problema dado y como se debera realizar el consumo del mismo:

1. Endpoint de creación de nuevas ruletas que devuelva el id de la nueva ruleta creada
R: (POST) http://localhost:8080/Roulette --> Permite la creacion de una ruleta, como parametros en el Json se puede enviar el campo "state" en true, si desea que la rule quede 
en estado abierta para realizar apuestas.

2. Endpoint de apertura de ruleta (el input es un id de ruleta) que permita las posteriores peticiones de apuestas, este debe devolver simplemente un estado que 
confirme que la operación fue exitosa o denegada.
R: (PUT) http://localhost:8080/Roulette/OpenRoulette/{idRuleta}  --> Este Endpoint permite realizar la apertura de una ruleta para la realizacion de apuestas. 
se debe enviar el campo "state" en true en el Json para actualizar el estado de la ruleta, ademas de enviar por parametro en la ruta el id de la ruleta para identificar a que ruleta
se le desea cambiar el estado.

3. Endpoint de apuesta a un número (los números válidos para apostar son del 0 al 36) o color (negro o rojo) de la ruleta una cantidad determinada de dinero (máximo
10.000 dólares) a una ruleta abierta. (nota: este enpoint recibe además de los parámetros de la apuesta, un id de usuario en los HEADERS asumiendo que el servicio que haga la petición ya realizo una
autenticación y validación de que el cliente tiene el crédito necesario para realizar la apuesta).
R: (POST) http://localhost:8080/Bet/{idRuleta}  --> Este Endpoint permite la creacion de apuestas para una ruleta dada, se deben enviar en el Json los campos: color(RED,BLACK), 
numero, monto. ademas de que en el header se debe enviar una key "usuario" con el valor del usuario que desea realizar la transaccion.

4. Endpoint de cierre apuestas dado un id de ruleta, este endpoint debe devolver el resultado de las apuestas hechas desde su apertura hasta el cierre. 
R: (PUT) http://localhost:8080/Roulette/CloseRoulette/{idRuleta}  --> Este Endpoint permite la actualizacion del estado de la ruleta para cerrar las apuestas. este devuelve
la lista de apuestas realizadas por dicha ruleta desde el momento en que se abre hasta que se cierra. se debe enviar en el Json el campo "state" en false para actualizar 
dicho estado, ademas de enviar desde la ruta el id de la ruleta para ser identificada.

5. Endpoint de listado de ruletas creadas con sus estados (abierta o cerrada). 
R: (GET) http://localhost:8080/Roulette  --> Este Endpoint retorna la lista de ruletas creadas con sus respectivos estados.







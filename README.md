[![Build Status](https://travis-ci.com/UNIZAR-30246-WebEngineering/lab4-websockets.svg?branch=master)](https://travis-ci.com/UNIZAR-30246-WebEngineering/lab4-websockets)
# Web Engineering 2020-2021 / Websockets
**In this assignment your PR must only modify the `README.md` file**.
Please, go to the [Wiki](https://github.com/UNIZAR-30246-WebEngineering/lab4-websockets/wiki) in order to get the instructions for this assignment.

## Historical background

The code implements the script DOCTOR of the [ELIZA](https://en.wikipedia.org/wiki/ELIZA) system and provides you a client and server that communicate each other by means of websockets. 
ELIZA is an early example of primitive natural language processing developed by [Joseph Weizenbaum](https://en.wikipedia.org/wiki/Joseph_Weizenbaum) between 1964 and 1966. 
ELIZA operated by processing users' responses to scripts, the most famous of which was DOCTOR, a simulation of a [Rogerian psychotherapist](https://en.wikipedia.org/wiki/Person-centered_therapy). 
Using almost no information about human thought or emotion, DOCTOR sometimes provided a startlingly human-like interaction.

## Primary goal

The objective is to complete `ElizaServerTest.java` by completing the test `onChat` that now is ignored. 
The `onChat` test must:
* Send a message from the client to the server that is running a Java implementation of the script DOCTOR.
* Check the client receives a DOCTOR question about your mental health (see examples in `Eliza.java`). 

You must remove `@Ignore` and place up to 6 lines of code to fulfil the task. 
The location of `Complete Me!!` is just a hint, but there are many possible solutions and locations.
`ElizaServerTest.java` includes the test `onOpen` that will provide you insights. 

## Secondary goals (:gift:)

Solutions must meet primary goals too. 

In progress:

- Replace the current implementation (Eclipse Grizzly) by a [Spring Websockets](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#websocket) implementation
- Turn the DOCTOR into a client of the server, so, the server acts only as message broker.

Proposed:

- Support STOMP in the server side and create a small client that uses it
- Support SockJS in the server side and show that xhr-polling can be used as transport instead of WebSocket when needed ([additional info](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#websocket-fallback-sockjs-client))
- Use the server as a relay server to connect to an external message broker (e.g. RabbitMQ, [additional info](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#websocket-stomp-handle-broker-relay)) 

Note: unless the goal specifies o disallows a specific framework you are free to replace the framework used in the original implementation with a different framework.

Manifest your intention first by a PR updating this `README.md` with your goal.
If you desist of your goal, release it by a PR so other fellow can try it. 

| NIA    | User name | Repo | Build Status | Improvement | Score
|--------|-----------|------|--------------|-------------|--------
| 760704 |[Álvaro García](https://github.com/Alvarogd6)|[Alvarogd6/lab4-websockets](https://github.com/Alvarogd6/lab4-websockets/tree/test)|![Build Status](https://travis-ci.com/Alvarogd6/lab4-websockets.svg?branch=test)|Replace the current implementation (Eclipse Grizzly) by a [Spring Websockets](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#websocket) implementation|
| 758906 |[Raúl Javierre](https://github.com/rauljavierre)|[rauljavierre/lab4-websockets](https://github.com/rauljavierre/lab4-websockets/tree/test)|![Build Status](https://travis-ci.com/rauljavierre/lab4-websockets.svg?branch=test)|Turn the DOCTOR into a client of the server, so, the server acts only as message broker|
| 757153 |[Fran Morés](https://github.com/Fran-sw) |[Fran-sw/lab4-websockets](https://github.com/Fran-sw/lab4-websockets/tree/test)      |![Build Status](https://travis-ci.com/Fran-sw/lab4-websockets.svg?branch=test)| |                      |
| 758803 |[Daniel González](https://github.com/Uncastellum/) |[Uncastellum/lab4-websockets](https://github.com/Uncastellum/lab4-websockets/tree/test)      |![Build Status](https://travis-ci.com/Uncastellum/lab4-websockets.svg?branch=test)| |                      |
| 757715 |[Hayk kocharyan](https://github.com/hayk99) |[hayk99/lab4-websockets](https://github.com/hayk99/lab4-websockets/tree/test)      |![Build Status](https://travis-ci.com/hayk99/lab4-websockets.svg?branch=test)| |                      |
| 758267 |[Pedro Allué](https://github.com/piter1902) |[piter1902/lab4-websockets](https://github.com/piter1902/lab4-websockets/tree/test)      |![Build Status](https://travis-ci.com/piter1902/lab4-websockets.svg?branch=test)| |                      |
| 739202 |[Luis Garcia](https://github.com/luisgg98) |[luisgg98/lab4-websockets](https://github.com/luisgg98/lab4-websockets/tree/test)      |![Build Status](https://travis-ci.com/luisgg98/lab4-websockets.svg?branch=test)| |                      |
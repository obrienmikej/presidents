# Overview

Springboot kotlin reference application. Data of USA presidents (data set presidents 1 to 5)

## Initialized project with

- [Spring initializer](https://start.spring.io)

## Docker

Manual steps (no scripts yet) to build and run container

`docker build -t presidents .`

Run container - Interactive mode

`docker run -p 8080:8080 -it presidents`

Run container - detached mode

`docker run -p 8080:8080 -d presidents`

## Verify

`curl http://localhost:8080/presidents`

`curl http://localhost:8080/presidents/homepage`

### API

- POST (add new President)

`curl -H "Content-Type: application/json" --request POST --data '{"name": "<update name>", "party": "<update party>"}' http://localhost:8080/presidents`

- GET (find existing President)

`curl http://localhost:8080/presidents/<unique id>`

- GET with filter (find existing President)

`curl "http://localhost:8080/presidents/<unique id>?utm_source=usa"`

- GET homepage

`curl http://localhost:8080/presidents/homepage`

- GET (all data)

`curl http://localhost:8080/presidents`

- DELETE (existing President)

~ `curl -X DELETE http://localhost:8080/presidents/<unique id>`

# Overview

Springboot kotlin reference application. Data of USA presidents.

## Initialized with the settings

- [Spring initializer](https://start.spring.io)
- project = `grade project`
- language = `kotlin`
- spring boot = `2.7.5`
- packaging = `jar`
- java = `17`
- dependencies = `Spring Web`

## Docker

Manual steps (no scripts yet) to build and run container

`docker build -t presidents/app .`

Run container

`docker run -p 8080:8080 presidents/app`

## Verify

`curl http://localhost:8080/presidents`

`curl http://localhost:8080/presidents/homepage`

### API

- POST (add new President)

`curl -H "Content-Type: application/json" --request POST --data '{"name": "< update name>", "party": "<update party>"}' http://localhost:8080/presidents`

- GET (find existing President)

`curl http://localhost:8080/presidents/<unique id>`

- GET with filter (find existing President)

`curl "http://localhost:8080/presidents/<unique id>?utm_source=usa"`

- GET homepage

`curl http://localhost:8080/presidents/homepage`

- GET (all data)

`curl http://localhost:8080/presidents`

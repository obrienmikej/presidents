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

- Manual steps (no scripts yet) to build and run container

`docker build -t presidents/app .`

Run container

`docker run -p 8080:8080 presidents/app`

## Verify

- `curl http://localhost:8080/presidents`
- `curl http://localhost:8080/presidents/homepage`

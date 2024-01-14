# Quarkus WebApp

This project was done as a technical challenge.

An api was developed in quarkus, where an endpoint is made available to
calculate the value of a sequence.

A minimal frontend in pure HTML and JS was done for simplicity.

## Running

### Frontend

Open the index.html file in [frontend/index.html](./frontend/index.html) in a browser.

A basic field and submit button directly query the 
<http://localhost:8080/labseq/n> endpoint, 
with __n__ being the provided number.

### Backend (bare metal)

Change into the __labseq__ directory and run the following command:

```shell
quarkus dev
```

The labseq endpoint is available on <http://localhost:8080/labseq/n>, with __n__ being the nth iteration of the sequence.

Swagger Open API is accessible at <http://localhost:8080/q/swagger-ui>

### Backend (docker)

Create an executable binary, which will be generated at the 
__target directory__:

```shell
quarkus build --native --no-tests -Dquarkus.native.container-build=true
```

From the root directory run the following command to create the docker image. This will copy the whole __target directory__ into the container.

```shell
docker build -t backend:1.0 -f docker/Dockerfile .
```

To run the backend as a container:

```shell
docker run -p 8080:8080 backend:1.0
```

## Important notes

The project was developed in VSCode with the quarkus and java extensions.

__Swagger is only accessible on bare metal version.__

Additional maven extensions were installed:

- [io.quarkus:quarkus-smallrye-openapi](https://quarkus.io/extensions/io.quarkus/quarkus-smallrye-openapi/), for Open API documentation
- [io.quarkus:quarkus-resteasy-reactive-jackson](https://quarkus.io/extensions/io.quarkus/quarkus-resteasy-reactive-jackson/), for data transference in JSON

```shell
quarkus extension add quarkus-smallrye-openapi
quarkus extension add quarkus-resteasy-reactive-jackson
```

Additional properties were defined on the application.properties file related to cors in order to allow querying the API from a different domain:

```ini
quarkus.http.cors=true
quarkus.http.cors.origins=*
```

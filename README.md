### How to build

Jar:
```shell
./mvnw clean package
```

Docker image:
```shell
docker build . --tag=socks:latest
```

Docker image using buildpacks (jib):

**Attention**: Dockerfile won't be used in this case, see
[Spring documentation](https://docs.spring.io/spring-boot/docs/current/maven-plugin/reference/htmlsingle/#build-image) and [GitHub repository](https://github.com/spring-projects/spring-boot/tree/master/spring-boot-project/spring-boot-tools/spring-boot-maven-plugin) for configuration
```shell
./mvnw spring-boot:build-image
```

Expect in the console:
```shell
Successfully built image 'docker.io/library/sock:0.0.1-SNAPSHOT'
```

### How to run

Jar:
```shell
java -jar ./target/sock-0.0.1-SNAPSHOT.jar
```

Docker image:
```shell
docker run -p 8090:8080 socks:latest
```

### How to verify:

```shell
curl --location --request GET 'http://localhost:8090/api/socks/hello'
```

Expect: 200 and 'hello'

### How to publish

```shell
docker tag socks:latest myrepo/socks:latest

docker push myrepo/socks
```

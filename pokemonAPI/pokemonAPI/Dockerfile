FROM amazoncorretto:17-alpine

COPY target/pokemonAPI-1.0.0.jar pokemonAPI-1.0.0.jar

COPY src/main/resources/pokemonConId.json /src/main/resources/pokemonConId.json

ENTRYPOINT ["java", "-jar", "/pokemonAPI-1.0.0.jar"]
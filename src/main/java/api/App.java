package api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
// import java.net.http.*;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.List;

public class App {

        private static final int SECONDS = 15;
        private static final String URL = "https://origam-exam-api.herokuapp.com/api/posts/";

        public static void main(String[] args) throws IOException, InterruptedException {

                 methodGet(args);
                // methodPost(args);
                // methodGetById(args);
                // methodDelete(args);
               // methodPatch(args);

        }

        public static void methodGet(String[] args) throws IOException, InterruptedException {

                HttpClient client = HttpClient.newHttpClient();

                HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create(URL))
                                .timeout(Duration.ofSeconds(SECONDS))
                                .GET()
                                .build();

                HttpResponse<String> response = client.send(request,
                                HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

                if (response.body() != null && (response.statusCode() == 200 || response.statusCode() == 201)) {
                        System.out.println("Requisicao realizada com sucesso, status: " + response.statusCode() + "\n");
                        System.out.println("Retorno:\n");

                        ObjectMapper mapper = new ObjectMapper();
                        List<RetornoAPI> lista = mapper.readValue(response.body(),
                                        new TypeReference<List<RetornoAPI>>() {
                                        });

                        for (RetornoAPI registro : lista) {
                                System.out.printf("id = %d, title = %s\n", registro.getId(), registro.getTitle());
                        }

                } else {
                        System.out.println("Ocorreu um erro durante a requisição");
                        System.out.println(response.statusCode());
                        System.out.println(response.body());

                }
        }

        public static void methodPost(String[] args) throws IOException, InterruptedException {

                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create(URL))
                                .timeout(Duration.ofSeconds(SECONDS))
                                .header("Content-Type", "application/json")
                                .POST(HttpRequest.BodyPublishers.ofString("{\"title\":\"teste alexandre 5\"}"))
                                .build();

                HttpResponse<String> response = client.send(request,
                                HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

                if (response.body() != null && (response.statusCode() == 200 || response.statusCode() == 201)) {
                        System.out.println("Requisicao realizada com sucesso, status: " + response.statusCode() + "\n");
                        System.out.println("Retorno:\n");

                        ObjectMapper mapper = new ObjectMapper();
                        RetornoAPI registro = mapper.readValue(response.body(), new TypeReference<RetornoAPI>() {
                        });

                        System.out.printf("Id do registro criado: %d \nTitulo do registro: %s\n", registro.getId(),
                                        registro.getTitle());

                } else {
                        System.out.println("Ocorreu um erro durante a requisição");
                        System.out.println(response.statusCode());
                        System.out.println(response.body());

                }
        }

        public static void methodGetById(String[] args) throws IOException, InterruptedException {

                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create(URL + "1"))
                                .timeout(Duration.ofSeconds(SECONDS))
                                .GET()
                                .build();

                HttpResponse<String> response = client.send(request,
                                HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

                if (response.body() != null && (response.statusCode() == 200 || response.statusCode() == 201)) {
                        System.out.println("Requisicao realizada com sucesso, status: " + response.statusCode() + "\n");
                        System.out.println("Retorno:\n");

                        ObjectMapper mapper = new ObjectMapper();
                        RetornoAPI registro = mapper.readValue(response.body(), new TypeReference<RetornoAPI>() {
                        });

                        System.out.printf("Id do registro: %d \nTitulo do registro: %s\n", registro.getId(),
                                        registro.getTitle());

                } else {
                        System.out.println("Ocorreu um erro durante a requisição");
                        System.out.println(response.statusCode());
                        System.out.println(response.body());

                }
        }

        public static void methodDelete(String[] args) throws IOException, InterruptedException {

                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create(URL + "1"))
                                .timeout(Duration.ofSeconds(SECONDS))
                                .DELETE()
                                .build();

                HttpResponse<String> response = client.send(request,
                                HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

                if (response.body() != null && (response.statusCode() == 200 || response.statusCode() == 201)) {
                        System.out.println("Requisicao realizada com sucesso, status: " + response.statusCode() + "\n");
                        System.out.println("Retorno:\n");

                        ObjectMapper mapper = new ObjectMapper();
                        RetornoDeleteAPI registro = mapper.readValue(response.body(),
                                        new TypeReference<RetornoDeleteAPI>() {
                                        });

                        System.out.println(registro.getMessage());

                } else {
                        System.out.println("Ocorreu um erro durante a requisição");
                        System.out.println(response.statusCode());
                        System.out.println(response.body());

                }

        }

        public static void methodPatch(String[] args) throws IOException, InterruptedException {

                HttpClient client = HttpClient.newHttpClient();

                HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create(URL + "1"))
                                .timeout(Duration.ofSeconds(SECONDS))
                                .header("Content-Type", "application/json")
                                .method("PATCH", HttpRequest.BodyPublishers
                                                .ofString("{\"title\":\"alexandre teste 5\"}"))
                                .build();

                HttpResponse<String> response = client.send(request,
                                HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

                if (response.body() != null && (response.statusCode() == 200 || response.statusCode() == 201)) {
                        System.out.println("Requisicao realizada com sucesso, status: " + response.statusCode() + "\n");
                        System.out.println("Retorno:\n");

                        ObjectMapper mapper = new ObjectMapper();
                        RetornoAPI registro = mapper.readValue(response.body(), new TypeReference<RetornoAPI>() {
                        });

                        System.out.printf("Id do registro atualizada: %d \nTitulo do registro: %s\n", registro.getId(),
                                        registro.getTitle());

                } else {
                        System.out.println("Ocorreu um erro durante a requisição");
                        System.out.println(response.statusCode());
                        System.out.println(response.body());

                }

        }
}

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP e buscar filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json";
        URI adress = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(adress).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // extrair só os dados que interessam (titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        // exibir e manipular os dados
        for (int i = 0; i < 3; i++) {
            Map<String, String> filme = listaDeFilmes.get(i);
            
            // Título
            System.out.print("\u001b[1m");
            System.out.print("╭");
            for (int j = 0; j < filme.get("title").length() + 2; j++) {
                System.out.print("─");
            }
            System.out.println("╮");
            System.out.println("│ " + filme.get("title") + " │");
            System.out.print("╰");
            for (int j = 0; j < filme.get("title").length() + 2; j++) {
                System.out.print("─");
            }
            System.out.println("╯\u001b[0m");

            // URL da imagem
            System.out.print("\u001b[3m URL da imagem: \u001b[0m");
            System.out.println(filme.get("image"));

            // Classificação
            double classificacao = Double.parseDouble(filme.get("imDbRating"));
            int numeroEstrelas = (int) Math.round(classificacao / 2);
            System.out.print("\u001b[33m Classificação: ");
            for (int n = 1; n <= 5; n++) {
                if (n <= numeroEstrelas) {
                    System.out.print("\u001b[1m★ ");
                } else {
                    System.out.print("☆ ");
                }
            }
            System.out.println("\u001b[0m\n");
        }
    }
}

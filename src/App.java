import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {

    public static void main(String[] args) throws Exception {

        String url ="https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14";
        ContentExtractor extractor = new NasaContentExtractor();
        //String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        //ContentExtractor extractor = new IMDBContentExtractor();

        var http = new ClientHttp();
        String json = http.dataSearch(url);

        List<Content> contents = extractor.extractContent(json);
        var generator = new StickerGenerator();

        for (int i = 0; i < 3; i++) {

            Content content = contents.get(i);

            InputStream inputStream = new URL(content.getUrlImage()).openStream();
            String fileName = "output/" + content.getTitle() + ".png";

            generator.create(inputStream, fileName);

            // Título
            System.out.print("\u001b[1m");
            System.out.print("╭");
            for (int j = 0; j < content.getTitle().length() + 2; j++) {
                System.out.print("─");
            }
            System.out.println("╮");
            System.out.println("│ " + content.getTitle() + " │");
            System.out.print("╰");
            for (int j = 0; j < content.getTitle().length() + 2; j++) {
                System.out.print("─");
            }
            System.out.println("╯\u001b[0m");

            // URL da imagem
            System.out.print("\u001b[3m URL da imagem: \u001b[0m");
            System.out.println(content.getUrlImage());

            // Classificação
            if (content.getRating() != null && !content.getRating().isEmpty()) {
                double classificacao = Double.parseDouble(content.getRating());
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
}
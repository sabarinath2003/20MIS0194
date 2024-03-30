import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            URIBuilder builder = new URIBuilder("http://28.244.56.144/test/companies/AMZ/categories/Laptop/products");
            builder.setParameter("top", "10");
            builder.setParameter("minPrice", "1");
            builder.setParameter("maxPrice", "10000");

            URI uri = builder.build();
            HttpGet request = new HttpGet(uri);

            request.addHeader("Content-Type", "application/json");

            httpClient.execute(request, response -> {
                int statusCode = response.getStatusLine().getStatusCode();
                System.out.println("Status Code : " + statusCode);
                String responseBody = EntityUtils.toString(response.getEntity());
                System.out.println("Response Body : " + responseBody);
                return null;
            });
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
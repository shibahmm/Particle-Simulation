import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import java.io.IOException;
import java.util.ArrayList;

public class ObstacleAPI {
    private final String BASE_URL = "http://127.0.0.1:5000/";
    OkHttpClient client = new OkHttpClient().newBuilder().build();
    ObjectMapper objectMapper = new ObjectMapper();
    
    public ArrayList<Obstacle> getObstacles() {
        ArrayList<Obstacle> obstacles = new ArrayList<>();
        try {
            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = RequestBody.create(mediaType, "");
            Request request = new Request.Builder()
                .url(BASE_URL + "obstacle")
                .method("GET", null)
                .build();
            Response response = client.newCall(request).execute();
            
            if (response.isSuccessful()) {
                String json = response.body().string();
                JsonNode jsonArray =  objectMapper.readTree(json);
                for (JsonNode obj : jsonArray) {
                    Obstacle obstacle = new Obstacle(
                        obj.get("id").asText(),
                        obj.get("x").asDouble(),
                        obj.get("y").asDouble(),
                        obj.get("radius").asDouble()
                    );
                    obstacles.add(obstacle);
                }
            }
        } catch (Exception ex) {
            System.out.println("Error while getting obstacles");
            ex.printStackTrace();
        }
        
        return obstacles;
    }

    public void addObstacle(Obstacle obs) {
        try {
            MediaType mediaType = MediaType.parse("application/json");
            String json = objectMapper.writeValueAsString(obs);
            RequestBody body = RequestBody.create(mediaType, json);
            Request request = new Request.Builder()
                .url(BASE_URL + "obstacle")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
            Response response = client.newCall(request).execute();
            
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
        } catch (Exception ex) {
            System.out.println("Error while adding the obstacle");
            ex.printStackTrace();
        }
    }

    public void deleteObstacle(String id) {
        try {
            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = RequestBody.create(mediaType, "");
            Request request = new Request.Builder()
                .url(BASE_URL + "obstacle/" + id)
                .method("DELETE", body)
                .build();
            Response response = client.newCall(request).execute();
            
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
        } catch (Exception ex) {
            System.out.println("Error while deleting the obstacle");
            ex.printStackTrace();
        }
    }
}

package edu.pwr.drozd.rsi_5.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.pwr.drozd.rsi_5.entity.Fruit;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Scanner;

public class FruitClient {

    public static Scanner sc = new Scanner(System.in);
    public static RestTemplate rt = new RestTemplate();

    public static void main(String[] args) {
        while (true) {
            System.out.println("What you want to do?");
            System.out.println("1. Add fruit");
            System.out.println("2. Remove fruit");
            System.out.println("3. Update fruit");
            System.out.println("4. Get fruit");
            System.out.println("5. Get all fruits");
            System.out.println("Choice: ");
            int choice = sc.nextInt();

            try {
                switch (choice) {
                    case 1:
                        addFruit();
                        break;
                    case 2:
                        removeFruit();
                        break;
                    case 3:
                        updateFruit();
                        break;
                    case 4:
                        getFruit();
                        break;
                    case 5:
                        getAllFruits();
                        break;
                    default:
                        unknownAnswer();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("\n\n=====\n");
        }
    }

    private static void unknownAnswer() {
        System.out.println("Unknown answer");
    }

    private static void getAllFruits() {
        ResponseEntity<List<Fruit>> all =
                rt.exchange(
                        "http://localhost:8080/fruits",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Fruit>>() {
                        });
        all.getBody().forEach(fruit -> {
            System.out.println("\n--");
            printFruitInfo(fruit);
        });
    }

    private static void printFruitInfo(Fruit fruit) {
        System.out.println("Name: " + fruit.getName());
        System.out.println("Price: " + fruit.getPrice());
        System.out.println("Sweetness: " + fruit.getSweetness());
    }

    private static void getFruit() {
        System.out.println("Input fruit name: ");
        String fruitName = sc.next();

        ResponseEntity<Fruit> fruitResponseEntity = rt.getForEntity("http://localhost:8080/fruits/" + fruitName, Fruit.class);

        printFruitInfo(fruitResponseEntity.getBody());
    }

    private static void updateFruit() throws JsonProcessingException {
        Fruit fruit = getFruitFromUser();

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(fruit);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(json, headers);

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        RestTemplate rt = new RestTemplate(requestFactory);

        rt.exchange("http://localhost:8080/fruits", HttpMethod.PATCH, entity, Void.class);
    }

    private static Fruit getFruitFromUser() {
        Fruit fruit = new Fruit();
        System.out.println("Input fruit name: ");
        fruit.setName(sc.next());
        System.out.println("Input fruit price: ");
        fruit.setPrice(sc.nextDouble());
        System.out.println("Input fruit sweetness: ");
        fruit.setSweetness(sc.nextInt());
        return fruit;
    }

    private static void removeFruit() {
        System.out.println("Input fruit name: ");
        String fruitName = sc.next();

        rt.delete("http://localhost:8080/fruits/" + fruitName);
    }

    private static void addFruit() {
        Fruit fruit = getFruitFromUser();

        String response = rt.postForObject("http://localhost:8080/fruits", fruit, String.class);
    }

}

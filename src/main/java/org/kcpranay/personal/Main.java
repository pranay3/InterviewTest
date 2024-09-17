package org.kcpranay.personal;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public static void main(String[] args) {
//        System.out.println("Starting WishList Manager Driver...");
//        WishlistManager wishlistManager = new WishlistManager();
//
//        wishlistManager.addWishList("a", new ArrayList<>( List.of("c","d")));
//        wishlistManager.addWishList("b", new ArrayList<>( List.of("d","a","c")));
//        wishlistManager.addWishList("c", new ArrayList<>( List.of("d","b")));
//        wishlistManager.addWishList("d", new ArrayList<>( List.of("c","a", "b")));
//
////        System.out.println(wishlistManager.has_mutual_first_choice("b"));
////        System.out.println(wishlistManager.has_mutual_first_choice("a"));
////        System.out.println(wishlistManager.has_mutual_pair_for_rank("d",1));
////        System.out.println(wishlistManager.has_mutual_pair_for_rank("c",5));
////        System.out.println(wishlistManager.has_mutual_pair_for_rank("g",5));
//
//        System.out.println(wishlistManager.changed_pairings("d",1));
//        System.out.println(wishlistManager.changed_pairings("b",2));
//        System.out.println(wishlistManager.changed_pairings("b",1));

//        System.out.println("Starting URLCompressor Driver...");
//
//        URLCompressor compressor = new URLCompressor(3);
//        String compressed = compressor.compress("section/how.to.write.a.java.program.in.one.day");
//        System.out.println(compressed);

//        PointsManagementSystem pointsManagementSystem = new PointsManagementSystem();
//        pointsManagementSystem.addTransaction(new Transaction("DANNON", 300L, 1725115452L));
//        pointsManagementSystem.addTransaction(new Transaction("UNILEVER", 200L, 1725115552L));
//        pointsManagementSystem.addTransaction(new Transaction("DANNON", -200L, 1725116452L));
//        pointsManagementSystem.addTransaction(new Transaction("MILLER COORS", 10000L, 1725125452L));
//        pointsManagementSystem.addTransaction(new Transaction("DANNON", 1000L, 1725125852L));
//        LOGGER.info("Original Balance is {}",pointsManagementSystem.getBalance());
//        pointsManagementSystem.spendPoints(5000L);
//        LOGGER.info("Updated Balance is {}",pointsManagementSystem.getBalance());

        StringGenerator generator = new StringGenerator();
        LOGGER.info(generator.generateStrings("/2022/{jan,feb,march}/report"));
        LOGGER.info(generator.generateStrings("read.txt{,.bak}"));
        LOGGER.info(generator.generateStrings("{,data,money,}"));
        LOGGER.info(generator.generateStrings("minimum{}change"));

        LOGGER.info(generator.generateStrings("/2022/{jan,feb,march}/report/{Mon,Tue,Wed}"));
//
//        LoanManagementSystem loanManagementSystem = new LoanManagementSystem();
//
//        loanManagementSystem.process();

//        String path = "/Users/pranay/work/data.txt";
//        try(BufferedReader br
//                    = new BufferedReader(new FileReader(path))) {
//            StringBuilder data = new StringBuilder();
//            String line;
//            while((line = br.readLine()) != null) {
//                data.append(line);
//                data.append(" ");
//            }
//            LOGGER.info(DataMasker.maskData(data.toString(), "\\d+-\\d+-\\d+-\\d+"));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

//        try {
//            HttpClient client = new HttpClient();
//            String resp = client.callGet("https://jsonplaceholder.typicode.com/posts");
//            JsonArray jsonArray = gson.fromJson(resp, JsonElement.class).getAsJsonArray();
//            LOGGER.info("Response is: {}", gson.toJson(jsonArray));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }


    }
}
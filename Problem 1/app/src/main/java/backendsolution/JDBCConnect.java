package backendsolution;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import org.apache.commons.text.StringEscapeUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JDBCConnect {

    public static void main(String[] args)
            throws ClassNotFoundException, SQLException, IOException {

        Logger logger = Logger.getLogger(JDBCConnect.class.getName());

        Class.forName("com.mysql.cj.jdbc.Driver");

        String dbUrl = null;
        String userName = null;
        String password = null;
        String inputJsonFile = null;
        String query = null;
        String inputJsonFileExtended = null;
        ResultSet resultSet = null;

        try (InputStream input = new FileInputStream("application.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            dbUrl = properties.getProperty("jdbc.dburl");
            userName = properties.getProperty("jdbc.username");
            password = properties.getProperty("jdbc.password");
            inputJsonFile = properties.getProperty("output.json.filePath");
            inputJsonFileExtended = properties.getProperty("output.json.filePathEx");
            query = "SELECT * FROM Business.CustomerInfo ci WHERE ci.PurchaseDate = CURRENT_DATE() AND ci.Location = 'Asia'";
        }

        try (Connection connection = DriverManager.getConnection(dbUrl, userName, password);
                Statement stmt = connection.createStatement()) {

            if (stmt.execute(query)) {
                resultSet = stmt.getResultSet();

                List<CustomerInfo> customersInfo = new ArrayList<>();

                while (resultSet.next()) {
                    CustomerInfo customerInfo = new CustomerInfo();
                    customerInfo.setCourseName(resultSet.getString(1));
                    customerInfo.setPurchaseDate(resultSet.getString(2));
                    customerInfo.setAmount(resultSet.getDouble(3));
                    customerInfo.setLocation(resultSet.getString(4));
                    customersInfo.add(customerInfo);
                }

                ObjectMapper oMapper = new ObjectMapper();
                oMapper.writeValue(
                        new File(inputJsonFile),
                        customersInfo);

                JSONObject jsonObject = new JSONObject();
                JSONArray jsonArray = new JSONArray();
                Gson gson = new Gson();

                for (int i = 0; i < customersInfo.size(); i++) {
                    jsonArray.add(gson.toJson(customersInfo.get(i)));
                }

                jsonObject.put("data", jsonArray);

                String unescapedString = StringEscapeUtils.unescapeJson(jsonObject.toJSONString())
                        .replace("\"{", "{").replace("}\"", "}");

                logger.info(unescapedString);

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(inputJsonFileExtended))) {
                    writer.write(unescapedString);

                } catch (Exception e) {
                    logger.severe("Exception occured while writing json file: " + e.getMessage());
                }

            }

        } catch (Exception e) {
            logger.severe("Error has occured. " + e.getMessage());
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException sqlEx) {
                    logger.severe("Unable to close resultset due to SQL Exception. " + sqlEx.getMessage());
                }
                resultSet = null;
            }
        }

    }

}

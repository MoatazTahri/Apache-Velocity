package com.leaf;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leaf.networkMS.Tools.NetworkUtils;
import com.leaf.tools.APILinks;
import com.leaf.tools.JsonUtils;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import org.junit.jupiter.api.*;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Set;

@SpringBootTest
public class IPApiTests {

    @BeforeEach
    void init() {
    }

    @DisplayName("internet is connected")
    @Test()
    @Order(1)
    void internet_is_connected() {
        Assertions.assertTrue(NetworkUtils.internetIsConnected());
    }

    @DisplayName("api ipinfo.io : wrong_address_ip")
    @Test()
    void wrong_address_ip_throws_IOException() {
        String address = "129.26.52.";
        Assertions.assertThrows(IOException.class, () -> JsonUtils.getJSON(String.format(APILinks.IPInfo_IO, address)));
    }

    @DisplayName("api ipinfo.io : valid_schema")
    @Test
    void api_ip_info_io_schema_is_valid() {
        String address = "196.70.226.178";
        Set<ValidationMessage> errors = null;
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonSchema schema = factory.getSchema(new FileInputStream("src/main/resources/JSONSchema/ipinfo.io-schema.json"));
            String json = JsonUtils.getJSON(String.format(APILinks.IPInfo_IO, address)).toString();
            JsonNode node = mapper.readTree(json);
            errors = schema.validate(node);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(0, errors.size());
    }

    @DisplayName("api ip-api.com : wrong_address_ip")
    @Test()
    void wrong_address_ip_throws_nothing() {
        String address = "129.26.52.";
        Assertions.assertDoesNotThrow(() -> JsonUtils.getJSON(String.format(APILinks.IPApi_Com, address)));
    }

    @DisplayName("api ip-api.com : valid_schema")
    @Test
    void ip_api_com_schema_is_valid() {
        String address = "196.70.226.178";
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        Set<ValidationMessage> errors = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonSchema schema = factory.getSchema(new FileInputStream("src/main/resources/JSONSchema/ip-api.com-schema.json"));
            String json = JsonUtils.getJSON(String.format(APILinks.IPApi_Com, address)).toString();
            JsonNode node = mapper.readTree(json);
            errors = schema.validate(node);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(0, errors.size());
    }
}

package com.personal.project.cash_control;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.personal.project.cash_control.dto.CashControl;

@JsonTest
class CashControlJsonTest {

    @Autowired
 	private JacksonTester<CashControl> json;

    @Test
    void cashCardSerializationTest() throws IOException {
    	
    	CashControl cashCard = new CashControl(99L, 123.45);
    	Resource resource = new ClassPathResource("test/cash_control/expected.json");
    	assertThat(json.write(cashCard)).isStrictlyEqualToJson(resource);
    	assertThat(json.write(cashCard)).hasJsonPathNumberValue("@.id");
    	assertThat(json.write(cashCard)).extractingJsonPathNumberValue("@.id")
    	.isEqualTo(99);
    	assertThat(json.write(cashCard)).hasJsonPathNumberValue("@.amount");
    	assertThat(json.write(cashCard)).extractingJsonPathNumberValue("@.amount")
    	.isEqualTo(123.45);

    }
    
    @Test
    void cashCardDeserializationTest() throws IOException {
       String expected = """
               {
                   "id":99,
                   "amount":123.45
               }
               """;
       assertThat(json.parse(expected))
               .isEqualTo(new CashControl(99L, 123.45));
       assertThat(json.parseObject(expected).id()).isEqualTo(99);
       assertThat(json.parseObject(expected).amount()).isEqualTo(123.45);
    }
}

package sopra.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import sopra.map_api.entity.Summit;
import sopra.map_api.service.MapApiService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@WebAppConfiguration
@SpringBootTest
public class MapServiceTest {

    private final MapApiService mapApiService = new MapApiService();

    @Test
    public void CheckCorrect_SummitInformaiton_fromUserInput() throws Exception {
        // given
        Summit testSummit = new Summit();
        testSummit.setName("Bristen");
        testSummit.setAltitude(776);
        testSummit.setX(695558);
        testSummit.setY(180480);

        // when
        List<Summit> liste = mapApiService.getSummitInformation("Bristen");

        // then
        assertEquals(testSummit.getName(), liste.get(0).getName());
        assertEquals(testSummit.getAltitude(), liste.get(0).getAltitude());
        assertEquals(testSummit.getX(), liste.get(0).getX());
        assertEquals(testSummit.getY(), liste.get(0).getY());
    }
}
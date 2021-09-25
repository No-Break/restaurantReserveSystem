package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.application.RestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class RestaurantControllerTest {
    @Autowired
    private RestaurantController restaurantController;

    @MockBean
    private RestaurantService restaurantService;

    private MockMvc mockMvc;

    @BeforeEach
    void beforEach() {
        mockMvc = MockMvcBuilders.standaloneSetup(restaurantController).build();
    }

    @Test
    public void list() throws Exception {
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(Restaurant.builder()
                .id(1004L)
                .name("Wonos")
                .address("Daegu")
                .build());

        given(restaurantService.getRestaurants()).willReturn(restaurants);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/restaurants"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Wonos")));
    }

    @Test
    public void detail() throws Exception {

        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("Wonos")
                .address("Daegu")
                .build();
        MenuItem menuItem = MenuItem.builder()
                .name("Kimchi")
                .build();
        restaurant.setMenuItems(Arrays.asList(menuItem));


        Restaurant restaurant2 = Restaurant.builder()
                .id(2020L)
                .name("Cyber Food")
                .address("Seoul")
                .build();

        given(restaurantService.getRestaurant(1004L)).willReturn(restaurant);
        given(restaurantService.getRestaurant(2020L)).willReturn(restaurant2);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/restaurants/1004"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Kimchi")));

    }

    @Test
    public void detailWithNotExisted() throws Exception {
        given(restaurantService.getRestaurant(404L))
                .willThrow(new RestaurantNotFoundException(404L));
        mockMvc.perform(
                MockMvcRequestBuilders.get("/restaurants/1004"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("{}"));
    }

    @Test
    public void createWithValidData() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/restaurants"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/restaurants/1234"))
                .andExpect(content().string("{}"));

        verify(restaurantService).addRestaurant(any());

    }

    @Test
    public void update() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.patch("/restaurants/1004")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"JOKER Bar\", \"address\" : \"Busan\"}"))
                .andExpect(status().isOk());

        verify(restaurantService).updateRestaurant(1004L, "JOKER Bar", "Busan");
    }

    @Test
    public void updateWithInvalidData() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.patch("/restaurants/1004")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"\", \"address\" : \"\"}"))
                .andExpect(status().isBadRequest());

    }

}

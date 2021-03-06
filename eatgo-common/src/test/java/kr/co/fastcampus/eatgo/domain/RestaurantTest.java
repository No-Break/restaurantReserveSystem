package kr.co.fastcampus.eatgo.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {

    @Test
    public void creation() {
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("bob")
                .address("Seoul")
                .build();
        assertThat(restaurant.getName()).isEqualTo("bob");
    }

    @Test
    public void information() {
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("wonos")
                .address("Daegu")
                .build();
        assertThat(restaurant.getInformation()).isEqualTo("wonos in Daegu");

    }

}

package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.application.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ReviewControllerTest {

    @Autowired
    private ReviewController reviewController;

    private MockMvc mockMvc;

    @Autowired
    private ReviewService reviewService;

    @BeforeEach
    void beforEach() {
        mockMvc = MockMvcBuilders.standaloneSetup(reviewController).build();
    }

    @Test
    public void create() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/restaurants/1/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"JOKER\", \"score\": 3, \"description\":\"good\"}"))
                .andExpect(status().isCreated());

        verify(reviewService).addReview(any());
    }

}

package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.MenuItem;
import kr.co.fastcampus.eatgo.domain.MenuItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class MenuItemServiceTest {

    private MenuItemService menuItemService;
    private MenuItemRepository menuItemRpository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        menuItemService = new MenuItemService(menuItemRpository);
    }


    @Test
    public void bulkUpdate() {
        List<MenuItem> menuItems = new ArrayList<MenuItem>();

        menuItems.add(MenuItem.builder().name("Kimchi").build());
        menuItems.add(MenuItem.builder().name("Gukbob").build());
        menuItemService.bulkUpdate(1L, menuItems);
        verify(menuItemRpository, times(2)).save(any());
    }

}

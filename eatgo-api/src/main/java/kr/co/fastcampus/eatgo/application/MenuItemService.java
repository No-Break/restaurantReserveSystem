package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.MenuItem;
import kr.co.fastcampus.eatgo.domain.MenuItemRepository;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;

@Service
public class MenuItemService {
    private MenuItemRepository menuItemRepositroy;

    public MenuItemService(MenuItemRepository menuItemRepositroy) {
        this.menuItemRepositroy = menuItemRepositroy;
    }

    public void bulkUpdate(Long restaurantId, List<MenuItem> menuItemList) {
        for (MenuItem menuItem : menuItemList) {
            menuItem.setRestaurantId(restaurantId);
            menuItemRepositroy.save(menuItem);
        }
        //Todo bulk update
    }
}


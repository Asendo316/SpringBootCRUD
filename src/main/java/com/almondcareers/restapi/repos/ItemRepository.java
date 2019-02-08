/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.almondcareers.restapi.repos;

import com.almondcareers.restapi.models.Item;
import com.almondcareers.restapi.utils.Manager;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Adeoluwa
 */
@Repository
public class ItemRepository {

    @Autowired
    JdbcTemplate template;

    /*Getting all Items from table*/
    public List<Item> getAllItems() {
        List<Item> items = template.query("select id, name,category from item", (result, rowNum) -> new Item(result.getInt("id"),
                result.getString("name"), result.getString("category")));
        if (Manager.LOGGER == true) {
            System.out.println("A get all request was made");
        }
        return items;
    }

    /**
     * Get Item by ID
     *
     * @param itemId
     * @return
     */
    public Item getItem(int itemId) {
        String query = "SELECT * FROM ITEM WHERE ID=?";
        Item item = template.queryForObject(query, new Object[]{itemId}, new BeanPropertyRowMapper<>(Item.class));
        if (Manager.LOGGER == true) {
            System.out.println("Request |=> " + query + "" + itemId);
        }
        return item;
    }

    /**
     * Add Item
     *
     * @param id
     * @param name
     * @param category
     * @return
     */
    public int addItem(int id, String name, String category) {
        String query = "INSERT INTO ITEM VALUES(?,?,?)";
        if (Manager.LOGGER == true) {
            System.out.println("Request |=> " + query + " id " + id + "name" + name + " category " + category);
        }
        return template.update(query, id, name, category);
    }

    /**
     * Delete Item
     *
     * @param id
     * @return
     */
    public int deleteItem(int id) {
        String query = "DELETE FROM ITEM WHERE ID =?";
        if (Manager.LOGGER == true) {
            System.out.println("Request |=> " + query + " id " + id);
        }
        return template.update(query, id);
    }
}

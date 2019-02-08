/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.almondcareers.restapi.controller;

import com.almondcareers.restapi.models.Item;
import com.almondcareers.restapi.repos.ItemRepository;
import com.almondcareers.restapi.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author Adeoluwa
 */
@RestController
public class ItemController {

    @Autowired
    ItemRepository itemRepo;
    Response response = new Response();

    /**
     * 
     * @return 
     */
    @RequestMapping("/getAllItems")
    @ResponseBody
    public List<Item> getAllItems() {
        return itemRepo.getAllItems();
    }

    /**
     * 
     * @param itemId
     * @return 
     */
    @RequestMapping("/getItem")
    @ResponseBody
    public Item getItem(@RequestParam("itemId") int itemId) {
        return itemRepo.getItem(itemId);
    }

    /**
     * 
     * @param id
     * @param name
     * @param category
     * @return 
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public Response addItem(@RequestParam("id") int id, @RequestParam("name") String name,
            @RequestParam("category") String category) {
        if (itemRepo.addItem(id, name, category) >= 1) {
            response.setResponseCode("00");
            response.setResponseMessage("Item Added Successfully");
        } else {
            response.setResponseCode("999");
            response.setResponseMessage("An error occored");
        }
        return response;
    }

    /**
     * 
     * @param itemId
     * @return 
     */
    @RequestMapping("/deleteItem")
    @ResponseBody
    public Response deleteItem(@RequestParam("itemId") int itemId) {
        if (itemRepo.deleteItem(itemId) >= 1) {
            response.setResponseCode("00");
            response.setResponseMessage("Item "+itemId+" Deleted Successfully");
        } else {
            response.setResponseCode("999");
            response.setResponseMessage("An error occored");
        }
        return response;
    }
}

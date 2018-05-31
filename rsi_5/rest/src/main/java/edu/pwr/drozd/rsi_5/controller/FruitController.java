package edu.pwr.drozd.rsi_5.controller;

import edu.pwr.drozd.rsi_5.dao.FruitDAO;
import edu.pwr.drozd.rsi_5.entity.Fruit;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@CrossOrigin()
@RestController()
@RequestMapping("/fruits")
public class FruitController {

    FruitDAO dao = new FruitDAO();

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<HashSet<Fruit>> getAllFruits() {
        return new ResponseEntity<>(dao.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Fruit> getFruit(@PathVariable("id") String name) {
        Fruit fruit = dao.getByName(name.toLowerCase());
        if (fruit == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(fruit, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Fruit> createFruit(@RequestBody Fruit fruit) {
        boolean added = dao.addFruit(fruit);
        if (!added) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Fruit> deleteFruit(@PathVariable("id") String name) {
        Fruit fruit = dao.getByName(name);
        boolean deleted = dao.removeFruit(fruit);
        if (!deleted) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PATCH, produces = "application/json")
    public ResponseEntity<Fruit> updateFruit(@RequestBody Fruit fruit) {
        boolean updated = dao.updateFruit(fruit);
        if (!updated) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

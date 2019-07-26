package org.ajou.realcoding.weathercrawler.weathercrawler.controller;

import java.util.ArrayList;
import java.util.List;

import org.ajou.realcoding.weathercrawler.weathercrawler.domain.Dog;
import org.springframework.web.bind.annotation.*;

@RestController
public class Dogcontroller {

    List<Dog> dogs=new ArrayList<>();

    @PostMapping("/dogs")
    public Dog createDog(@RequestBody Dog dog)
    {
        dogs.add(dog);
        return dog;
    }
    @GetMapping("/dogs") //   /dogs?name=ian&type=martiz
    public Dog fingDog(@RequestParam String name,String type)
    {
        for(int i=0;i<dogs.size();i++)
        {
            if(dogs.get(i).getName().equals(name))
            {

                return dogs.get(i);
            }

        }
        return null;
    }

    @DeleteMapping("/dogs/{name}")
    public void deleteDog(@RequestParam String name)
    {
        for(int i=0;i<dogs.size();i++)
        {
            if(dogs.get(i).getName().equals(name))
            {
                dogs.remove(dogs.get(i));

            }

        }

    }
    @PutMapping("/dogs/{name}")
    public Dog updateDog(@RequestBody Dog dog)
    {
        for(int i=0;i<dogs.size();i++)
        {
            if(dogs.get(i).getName().equals(dog.getName()))
            {

                dogs.get(i).setKind(dog.getKind());
                break;
            }

        }
        return dog;
    }

}

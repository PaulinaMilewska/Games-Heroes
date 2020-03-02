package games.weapons;

import games.heroes.Hero;
import games.heroes.HeroDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Integer.parseInt;

@Controller
public class WeaponController {
    private List<Weapon> weaponList;
    private List<Hero> heroList;
    @Autowired
    private WeaponDao weaponDao;
    @Autowired
    private HeroDao heroDao;


    public WeaponController(WeaponDao weaponDao, HeroDao heroDao) {
        this.weaponDao= weaponDao;
        this.heroDao = heroDao;
        weaponList = weaponDao.getWeapons();
        heroList = heroDao.getHeroes();
    }



    @RequestMapping(value = "/weaponform", method = RequestMethod.GET)
    public String showform(Model model) {
        heroList = heroDao.getHeroes();
        model.addAttribute("weapon", new Weapon());
        model.addAttribute("heroList", heroList);
        model.addAttribute("heroIds", new ArrayList<>());
        return "weapon/weaponform";
    }

    @RequestMapping("/saveweapon")
    public ModelAndView save(@ModelAttribute(value = "weapon") Weapon weapon,
                             @ModelAttribute(value = "heroIds") ArrayList<String> heroIds) {
        System.out.println();
        List<Integer> idsToIntList = new ArrayList<>();
        for (String idToConvert : heroIds) {
            Integer id = Integer.parseInt(idToConvert);
            idsToIntList.add(id);
        }

        Set<Hero> heroHashSet = new HashSet<>();
        for (Hero hero : heroList) {
            if (weapon.getId() == null) {
                if (idsToIntList.contains(hero.getId())) {
                    heroHashSet.add(hero);
                }
            }
            weapon.setHeroSet(heroHashSet);

            if (weapon.getId() == null) {
                weapon.setId(weaponList.size());
                weaponDao.createWeapon(weapon);
                weaponList = weaponDao.getWeapons();

                weaponList.add(weapon);
            } else {
                weaponDao.updateWeapon(weapon);
                weaponList.set(weapon.getId() - 1, weapon);
            }
        }
        return new ModelAndView("redirect:/viewweapon");
    }

    @PostMapping(value = "/editweapon")
    public ModelAndView edit(@RequestParam(value = "weapon_id") String weapon_id) {
        Weapon weapon = getWeaponById(parseInt(weapon_id));
        return new ModelAndView("weapon/weaponform", "weapon", weapon);
    }


    @RequestMapping(value = "/deleteweapon", method = RequestMethod.POST)
    public ModelAndView delete(@RequestParam(value = "weapon_id") String weapon_id) {
        Weapon weaponToDelete = getWeaponById(parseInt(weapon_id));
        weaponDao.deleteWeapon(weaponToDelete);
        weaponList.remove(weaponToDelete);
        return new ModelAndView("redirect:/viewweapon");
    }

    @RequestMapping("/viewweapon")
    public ModelAndView viewweapon(Model model) {
        weaponList = weaponDao.getWeapons();
        return new ModelAndView("weapon/viewweapon", "weaponList", weaponList);
    }

    private Weapon getWeaponById(@RequestParam int weapon_id) {
        return weaponList.stream().filter(f -> f.getId() == weapon_id).findFirst().get();
    }


}

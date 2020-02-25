package games.weapons;

import games.heroes.Hero;
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
    WeaponDao weaponDao;

    public WeaponController() {
        weaponList = new ArrayList<>();
        heroList = new ArrayList<>();
    }

    @RequestMapping(value = "/weaponform", method = RequestMethod.GET)
    public String showform(Model model) {
        model.addAttribute("weapon", new Weapon());
        return "weapon/weaponform";
    }

    @RequestMapping("/saveweapon")
    public ModelAndView save(@ModelAttribute(value = "weapon") Weapon weapon,
                             @ModelAttribute(value = "heroIds") ArrayList<Integer> heroIds) {
        Set<Hero> heroHashSet = new HashSet<>();
        for (Hero hero : heroList) {
            if (weapon.getId() == 0) {
                if (heroIds.contains(hero.getId())) {
                    heroHashSet.add(hero);
                }
            }
            weapon.setHeroSet(heroHashSet);

            if (weapon.getId() == 0) {
                weaponDao.createWeapon(weapon);

                weapon.setId(weaponList.size());
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
        return new ModelAndView("viewweapon", "weaponList", weaponList);
    }

    private Weapon getWeaponById(@RequestParam int weapon_id) {
        return weaponList.stream().filter(f -> f.getId() == weapon_id).findFirst().get();
    }
    

}

package games.heroes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HeroController {

    @Autowired
    private HeroDao heroDao;

    private List<Hero> list = new ArrayList<>();

    public HeroController() {
    }

    @RequestMapping("/")
    public String indexGet() {
        return "index";
    }

    @RequestMapping("/herohero")
    public String indexGetss() {
        return "hero/herohero";
    }

    @RequestMapping(value = "/heroform", method = RequestMethod.GET)
    public String showform(Model model) {
        model.addAttribute("hero", new Hero());
        return "hero/heroform";
    }

    @RequestMapping("/savehero")
    public ModelAndView saveHero(@ModelAttribute(value = "hero") Hero hero) {

        if (hero.getId() == null) {
            hero.setId(1);
            try {
                heroDao.createHero(hero);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            list.add(hero);
        } else {
            heroDao.updateHero(hero);
            int index = hero.getId();
            hero.setId(index);
        }
        return new ModelAndView("redirect:/viewhero");
    }

    @RequestMapping(value = "/edithero")
    public ModelAndView edit(@RequestParam(value = "hero_id") String hero_id) {
        Hero hero = getHeroById(Integer.parseInt(hero_id));

        return new ModelAndView("hero/heroform", "hero", hero);
    }


    private Hero getHeroById(@RequestParam int hero_id) {
        return list.stream().filter(f -> f.getId() == hero_id).findFirst().get();
    }

    @RequestMapping(value = "/deletehero", method = RequestMethod.POST)
    public ModelAndView delete(@RequestParam(value = "hero_id") String hero_id) {
        System.out.println("iiiiiiiiidddddddddddddd    "+hero_id);
        Hero heroToDelete = getHeroById(Integer.parseInt(hero_id));
        list.remove(heroToDelete);
        heroDao.deleteHero(heroToDelete);

        return new ModelAndView("redirect:/viewhero");
    }

    @RequestMapping("/viewhero")
    public ModelAndView viewheroes(Model model) {
        list = heroDao.getHeroes();
        return new ModelAndView("hero/viewhero", "list", list);
    }


}

package games.vehicles;

import games.heroes.Hero;
import games.heroes.HeroDao;
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
public class VehicleController {

    private List<Vehicle> list = new ArrayList<>();
    private List<Hero> listHero = new ArrayList<>();

    @Autowired
    private HeroDao heroDao;
    @Autowired
    private VehicleDao vehicleDao;

    public VehicleController(VehicleDao vehicleDao, HeroDao heroDao ) {
        this.vehicleDao = vehicleDao;
        this.heroDao = heroDao;
        listHero = heroDao.getHeroes();
    }


    public VehicleController() {
    }

    @RequestMapping(value = "/vehicleform", method = RequestMethod.GET)
    public String showform(Model model) {
        listHero = heroDao.getHeroes();
        model.addAttribute("vehicle", new Vehicle());
        model.addAttribute("listHero", listHero);
        return "vehicle/vehicleform";
    }

    @RequestMapping("/savevehicle")
    public ModelAndView saveVehicle(@ModelAttribute(value = "vehicle") Vehicle vehicle,
                                @ModelAttribute(value = "hero.id") String hero_id_Sting) {
        int hero_id = Integer.parseInt(hero_id_Sting);
        listHero = heroDao.getHeroes();

        vehicle.setHero(listHero.get(hero_id));
        if (vehicle.getId() == 0) {

            vehicle.setId(list.size()+1);
            vehicleDao.createVehicle(vehicle);
            vehicle.setId(list.size());
            list.add(vehicle);

        } else {

            listHero = heroDao.getHeroes();
            int index = vehicle.getId();
            vehicle.setHero(listHero.get(index));
            vehicleDao.updateVehicle(vehicle);

//            }
            list.set(vehicle.getId() - 1, vehicle);
            updateVehicleInList(vehicle);
        }
        return new ModelAndView("redirect:/viewvehicle");
    }

    Hero hero;

    @RequestMapping(value = "/deletevehicle", method = RequestMethod.POST)
    public ModelAndView delete(@RequestParam(value = "vehicle_id") String vehicle_id) {
        Integer vehicleId = Integer.parseInt(vehicle_id);
        Vehicle vehicleToDelete = getVehicleById(vehicleId);

        vehicleDao.deleteVehicle(vehicleToDelete);
        list.remove(vehicleToDelete);
        return new ModelAndView("redirect:/viewvehicle");
    }

    @RequestMapping("/viewvehicle")
    public ModelAndView viewvehicles(Model model) {
        System.out.println();
        list = vehicleDao.getVehicles();
        return new ModelAndView("vehicle/viewvehicle", "list", list);
//        return new ModelAndView("hero/herohero");
//        return new ModelAndView("hero/viewvehicle", "list", list);
    }

    @RequestMapping("/viewvehicles")
    public String viewvehicles() {

        return "vehicle/viewvehicles";
//        return "hero/herohero";
    }

    private Vehicle getVehicleById(@RequestParam Integer vehicle_id) {
        return list.stream().filter(f -> f.getId() == vehicle_id).findFirst().get();
    }

    private void updateVehicleInList(Vehicle vehicle) {
        Vehicle vehicleTemp = getVehicleById(vehicle.getId());
        vehicleTemp.setName(vehicle.getName());
        vehicleTemp.setSpeed(vehicle.getSpeed());
        vehicleTemp.setValue(vehicle.getValue());
        vehicleTemp.setHero(vehicle.getHero());

    }

}

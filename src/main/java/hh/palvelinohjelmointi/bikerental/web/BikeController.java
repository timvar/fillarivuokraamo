package hh.palvelinohjelmointi.bikerental.web;


import hh.palvelinohjelmointi.bikerental.domain.*;
import hh.palvelinohjelmointi.bikerental.repository.BikeRepository;


import hh.palvelinohjelmointi.bikerental.repository.CategoryRepository;
import hh.palvelinohjelmointi.bikerental.repository.RentalRepository;
import hh.palvelinohjelmointi.bikerental.repository.RentalStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Bike rental
 */
@Controller
public class BikeController {

    @Autowired
    private BikeRepository bikeRepository;

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RentalStatusRepository rentalStatusRepository;

    /**
     * Login page
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * Home page
     */
    @GetMapping(value="*")
    public String showHomePage(){
        return "index";
    }

    /**
     * Customer bike rental form
     * @return
     */
    @GetMapping("/add-rental")
    public String addRental(Model model){
        model.addAttribute("rental", new Rental());
        model.addAttribute("bikes", bikeRepository.findAll());
        model.addAttribute("statusoptions", rentalStatusRepository.findRentalStatusByStatusName("active").get());
        return "rentalform";
    }

    /**
     * Customer bike rental form -> save
     * @param rental
     * @return
     */
    @PostMapping(value = "/rentalform-save")
    public String saveRental(Rental rental) {

        RentalStatus rentalStatusActive = rentalStatusRepository.findRentalStatusByStatusName("active").get();

        /** Check bike availability in given rental period **/
        long rentalCount = rentalRepository.countRentalsByStartDayIsLessThanEqualAndEndDayIsGreaterThanEqualAndBikeAndStatusIs(
                rental.getEndDay(), rental.getStartDay(), rental.getBike(), rentalStatusActive);

        /** Total number of bikes to rent **/
        int bikesForRent = bikeRepository.findById(rental.getBike().getBikeId()).get().getBikeQty();

        String returnUrl = "";

        /** If > 0, there are bikes available in given rental period **/
        if (bikesForRent - rentalCount > 0) {
            Rental savedRental = rentalRepository.save(rental);
            String savedRentalId = Long.toString(savedRental.getRentalId());
            returnUrl = "redirect:/rentalsuccess/"+savedRentalId;
        }

        /** no bikes available, go to search page **/
        else {
            returnUrl = "redirect:/availability";
        }

        return returnUrl;
    }

    /**
     * Bike successfully rented, confirmation to customer
     * @param rentalId
     * @param model
     * @return
     */
    @GetMapping("/rentalsuccess/{id}")
    public String showRentalSuccess(@PathVariable("id") Long rentalId, Model model) {

        model.addAttribute("rental", rentalRepository.findById(rentalId).get());
        return "rentalsuccess";
    }

    /**
     * No bikes available for given rental period, go to availability search page
     * @param model
     * @return
     */
    @GetMapping("/availability")
    public String showAvailability(Model model) {

        BikeSearch bikeSearch = new BikeSearch();
        model.addAttribute("searchinfo", bikeSearch);
        model.addAttribute("bikes", bikeRepository.findAll());
        return "availability";
    }

    /**
     * Search bikes to rent by given start date
     * @param model
     * @return
     */
    @PostMapping("/search-bikes")
    public String showBikeAvailability(BikeSearch bikeSearch, Model model) {

        /** List for showing bike model and number of available bikes in a given date **/
        List<Availability> availabilityList = new ArrayList<>();

        /** Use calendar starting from given search start date **/
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(bikeSearch.getStartDay());

        RentalStatus rentalStatusActive = rentalStatusRepository.findRentalStatusByStatusName("active").get();

        /** create list of Availability objects (date, availability) for 14 days starting from given search start date **/
        for (int i = 0; i < 14; i++) {

            Availability availability = new Availability();
            /** set date **/
            availability.setDate(calendar.getTime());

            /** search availability for the above date **/
            availability.setAvailability(bikeSearch.getBike().getBikeQty() -
                rentalRepository.countRentalsByStartDayIsLessThanEqualAndEndDayIsGreaterThanEqualAndBikeAndStatusIs(
                        calendar.getTime(), calendar.getTime(), bikeSearch.getBike(), rentalStatusActive));

            /** add Availability object to the list **/
            availabilityList.add(availability);

            /** increase date by one day **/
            calendar.add(Calendar.DATE,1);
        }

        model.addAttribute("bikes", availabilityList);
        return "showavailability";
    }

    /**
     * show bikes to customer
     * @param model
     * @return
     */

    @GetMapping(value="/show-bikes")
    public String showCtmBikeList(Model model) {
        model.addAttribute("bikes", bikeRepository.findAll());
        return "ctm-bikelist";
    }

    /** .................................................................
     *
     * Admin functionalities
     *
     * ...................................................................
     */

    /**
     * Admin home page
     */
    @GetMapping("admin/home")
    public String showAdminHome() {
        return "admin-home";
    }

    /**
     * Admin add, update and delete bikes main page
     * @param model
     * @return
     */
    @GetMapping(value="/admin/bikelist")
    public String showBikeList(Model model) {
        model.addAttribute("bikes", bikeRepository.findAll());
        return "bikelist";
    }

    /**
     * Admin add bike
     * @param model
     * @return
     */
    @GetMapping(value = "/admin/add-bike")
    public String addBike(Model model){
        model.addAttribute("bike", new Bike());
        model.addAttribute("categories", categoryRepository.findAll() );
        return "addbike";
    }

    /**
     * Add -> Save bike
     * @param bike
     * @return
     */
    @PostMapping(value = "/admin/save")
    public String saveBike(Bike bike){
        bikeRepository.save(bike);
        return "redirect:/admin/bikelist";
    }

    /**
     * Admin edit bike
     * @param bikeId
     * @param model
     * @return
     */
    @GetMapping(value = "/admin/edit/{id}")
    public String editBike(@PathVariable("id") Long bikeId, Model model) {
        model.addAttribute("bike", bikeRepository.findById(bikeId));
        model.addAttribute("categories", categoryRepository.findAll());
        return "editbike";
    }

    /**
     * Admin show rentals (edit, update)
     * @param model
     * @return
     */
    @GetMapping(value="/admin/rentallist")
    public String showRentalList(Model model) {
        model.addAttribute("rentals", rentalRepository.findAll());
        return "rentallist";
    }

    /**
     * Edit rental (change start, end date or change status)
     * @param rentalId
     * @param model
     * @return
     */
    @GetMapping("/admin/editrental/{id}")
    public String editRental(@PathVariable("id") Long rentalId, Model model) {
        model.addAttribute("rental", rentalRepository.findById(rentalId));
        model.addAttribute("statusnames", rentalStatusRepository.findAll());
        return "editrental";
    }

    /**
     * Admin edit -> update rental
     * @param rental
     * @return
     */
    @PostMapping("/admin/update-rental")
    public String updateRental(Rental rental){
        rentalRepository.save(rental);
        return "redirect:rentallist";
    }

    /**
     * Admin edit -> update bike
     * @return
     */
    @PostMapping("/admin/update")
    public String updateBike(Bike bike){
        bikeRepository.save(bike);
        return "redirect:bikelist";
    }

    /**
     * Admin delete bike (confirmation page before delete operation)
     * @return
     */
    @GetMapping("/admin/confirm-delete/{id}")
    public String confirmdeleteBike(@PathVariable("id") Long bikeId, Model model) {
        model.addAttribute("bike", bikeRepository.findById(bikeId));
        model.addAttribute("categories", categoryRepository.findAll());
        return "confirm-delete";
    }

    /**
     * Admin delete bike (after confirmation page)
     * @param bike
     * @return
     */
    @PostMapping("/admin/delete")
    public String deleteBike(Bike bike){
        bikeRepository.deleteById(bike.getBikeId());
        return "redirect:bikelist";
    }
}

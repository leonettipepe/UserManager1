package net.leonetti.usermanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.leonetti.usermanager.model.User;
import net.leonetti.usermanager.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	private static final int MAX_ROWS_PER_PAGE = 5;

	@Autowired(required = true)
	@Qualifier(value = "userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newUser(Model model) {
		model.addAttribute("user", new User());
		return "user";
	}
	
	@RequestMapping(value = "/users/add", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("user") User user) {
		if (user.getId() == null) {
			this.userService.addUser(user);
		} else {
			this.userService.updateUser(user);
		}

		return "redirect:/users";
	}

	@RequestMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") int id) {
		this.userService.deleteUser(id);

		return "redirect:/users";
	}

	@RequestMapping("edit/{id}")
	public String editUser(@PathVariable("id") int id, Model model) {
		model.addAttribute("user", this.userService.getUser(id));
		return "user";
	}

	@RequestMapping("userdata/{id}")
	public String userData(@PathVariable("id") int id, Model model) {
		model.addAttribute("user", this.userService.getUser(id));

		return "userdata";
	}

	/*@RequestMapping(value = "users", method = RequestMethod.GET)
	public String listUsers(Model model) {
		model.addAttribute("listUsers", this.userService.listUsers());
		return "users";
	}*/

	@RequestMapping(value = { "searchUser" })
	public String searchUser(@RequestParam(required = false, value = "searchName") String searchName, Model model) {
		List<User> usersList = userService.searchByName(searchName);
		model.addAttribute("usersList", usersList);
		return "search";
	}

	@RequestMapping(value = "/users")
	public String listOfUsers(@RequestParam(required = false) Integer page, Model model) {

		//ModelAndView modelAndView = new ModelAndView("userList");

		List<User> users = userService.listUsers();
		PagedListHolder<User> pagedListHolder = new PagedListHolder<User>(users);
		pagedListHolder.setPageSize(MAX_ROWS_PER_PAGE);

		model.addAttribute("maxPages", pagedListHolder.getPageCount());

		if (page == null || page < 1 || page > pagedListHolder.getPageCount()) {
			page = 1;
		}
		model.addAttribute("page", page);
		if (page == null || page < 1 || page > pagedListHolder.getPageCount()) {
			pagedListHolder.setPage(0);
			model.addAttribute("listUsers", pagedListHolder.getPageList());
		} else if (page <= pagedListHolder.getPageCount()) {
			pagedListHolder.setPage(page - 1);
			model.addAttribute("listUsers", pagedListHolder.getPageList());
		}
		return "users";
	}

}

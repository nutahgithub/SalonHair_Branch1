package com.hairteen.hung.web.controller.account;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hairteen.hung.web.form.AccountForm;
import com.hairteen.hung.web.service.AccountService;
import com.hairteen.hung.web.utils.WebUtils;
import com.hairteen.hung.web.validator.AccountValidator;

@Controller
public class AccountController {

    @Autowired
    private AccountValidator accountValidator;

    @Autowired
    private AccountService accountService;

    // Set a form validator
    @InitBinder
    protected void initBinder(WebDataBinder dataBinder) {
        // Form mục tiêu
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        //System.out.println("Target=" + target);
 
        if (target.getClass() == AccountForm.class) {
            dataBinder.setValidator(accountValidator);
        }
    }

    /**
     * Init login screen.
     * @param model
     * @return
     */
    @RequestMapping(value = {"/", "/index", "/login_view"}, method = RequestMethod.GET)
    public String index(Model model) {
        return "login";
    }

    /**
     * Home screen.
     * @param model
     * @return
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model) {
        return "home";
    }

    /**
     * Login register view.
     * @param model
     * @return
     */
    @RequestMapping(value = "/login_register_view", method = RequestMethod.GET)
    public String loginRegisterView(Model model) {

        AccountForm accountForm = new AccountForm();
        model.addAttribute("accountForm", accountForm);
        return "login_register";
    }

    /**
     * Do login register.
     * @param model
     * @param accountForm
     * @param result
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/login_register", method = RequestMethod.POST)
    public String loginRegisterSave(Model model, 
            @ModelAttribute("accountForm") @Validated AccountForm accountForm,
            BindingResult result,
            final RedirectAttributes redirectAttributes) {

        // Validate result
        if (result.hasErrors()) {
            return "login_register";
        }

        try {
            accountService.saveAccount(accountForm);
        }catch (Exception e) {
            System.out.println(e);
            return "login_register";
        }
        return "login";
    }

    /**
     * Erorr page.
     * @param model
     * @param principal
     * @return
     */
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {

        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            String userInfo = WebUtils.toString(loginedUser);
            model.addAttribute("userInfo", userInfo);

            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);
        }
        return "403Page";
    }

}

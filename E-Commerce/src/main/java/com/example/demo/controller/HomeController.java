package com.example.demo.controller;

import com.example.demo.production.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private final ProductRepository productRepository;

    public HomeController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("")
    public String showHomeView(){
        return "redirect:/";
    }

    @GetMapping("/")
    public String showHome(Model model,
                           HttpServletRequest request,
                           RedirectAttributes redirect){

        request.getSession().setAttribute("productList", null);
        if(model.asMap().get("success") != null)
            redirect.addFlashAttribute("success",model.asMap().get("success").toString());

        return "redirect:/page/1";

    }

    @GetMapping("/page/{pageNumber}")
    public String pagination(Model model,
                             @PathVariable("pageNumber") int pageNumber,
                             HttpServletRequest request) {
        PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("productList");
        int pagesize = 6;
        List<Product> list = (List<Product>) productRepository.findAll();
        System.out.println(list.size());
        if (pages == null){
            pages = new PagedListHolder<>(list);
            pages.setPageSize(pagesize);
        }
        else {
            final int goToPage = pageNumber - 1;
            if (goToPage <= pages.getPageCount() && goToPage >= 0) {
                pages.setPage(goToPage);
            }
        }

        request.getSession().setAttribute("productList", pages);
        int current = pages.getPage() + 1;
        int begin = Math.max(1, current - list.size());
        int end = Math.min(begin + 5, pages.getPageCount());
        int totalPageCount = pages.getPageCount();
        String baseUrl = "/page/";

        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("baseUrl", baseUrl);
        model.addAttribute("products", pages);

        return "home/index";
    }

    @GetMapping("/product/{id}")
    public String showProduct(Model model,
                              @PathVariable("id") long id){
        Product product = productRepository.findById(id).get();
        model.addAttribute("product", product);
        return "home/product";
    }

    @GetMapping("/cart/{id}")
    public String cartPro(Model model,
                          @PathVariable("id") long id){
        Product product = productRepository.findById(id).get();
        model.addAttribute("product", product);
        return "home/cart";

    }


}

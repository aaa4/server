package github.aaa4.server.web.controllers.web;

import github.aaa4.server.entity.Account;
import github.aaa4.server.repo.AccountPagingRepository;
import github.aaa4.server.services.AccountAndRoleService;
import github.aaa4.server.web.controllers.web.DTO.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/users")
class RegistrationController {


    @Autowired
    AccountAndRoleService service;

    @Autowired
    AccountPagingRepository accountPagingRepository;

    @GetMapping("/registration")
    public void registration() {
        //get registration form
    }


    @GetMapping
    public List<AccountDTO> findAll() {
        List<AccountDTO> accounts = new ArrayList<>();
        List<Account> allAccounts = service.findAll();
        for (int i = 0; i < 10000; i++)
            accounts.add(new AccountDTO(allAccounts.get(i)));
        return accounts;
    }

    @GetMapping(path = "/pages", params = {"page", "size"})
    public Page<AccountDTO> findPaginated(@RequestParam("page") int page, @RequestParam("size") int size,
                                          UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        //todo: add paginated responce from service here

        //Pageable pageRequest = PageRequest.of(0, 30);
        Pageable pageRequest = PageRequest.of(0, size, Sort.by("name"));

        System.out.println("page number is: " + page + " page size is: " + size);

        Page<Account> currentPage = accountPagingRepository.findAll(pageRequest);
        Page<AccountDTO> result = currentPage.map(account -> new AccountDTO(account));
        return result;
    }

    //http://localhost:8080/users/paginated?sort=created&order=desc&page=0
    @GetMapping(path = "/paginated")
    public Page<AccountDTO> getPage(@RequestParam("sort") String sort,
                                    @RequestParam("order") String order,
                                    @RequestParam("page") int pageNumber) {
        System.out.println("params; sort: " + sort + " order " + order + " page " + pageNumber);
        Pageable pageRequest;
        if (order.equals("asc"))
            pageRequest = PageRequest.of(pageNumber, 30, Sort.by(sort).ascending());
        else
            pageRequest = PageRequest.of(pageNumber, 30, Sort.by(sort).descending());

      /*  return accountPagingRepository.findAll(pageRequest)
                .stream()
                .map(account -> new AccountDTO(account))
                .collect(Collectors.toList());*/
        return accountPagingRepository.findAll(pageRequest)
                .map(account -> new AccountDTO(account));
    }

    @GetMapping("/{id}")
    public Account findById(@PathVariable("id") UUID id) {
        return RestPreconditions.checkFound(service.findDistinctById(id).get());
    }

    @PostMapping("/registration")
    public void postRegistration() {

    }
}

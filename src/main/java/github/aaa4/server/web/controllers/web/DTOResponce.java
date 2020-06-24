package github.aaa4.server.web.controllers.web;


import github.aaa4.server.web.controllers.web.DTO.AccountDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * custom class for connecting list<AccountDTO> with angular front
 *
 */
public class DTOResponce {

    //items at one page (see paginated set up at controller)
    private List<AccountDTO> items;
    //number of items at all
    private long totalCount;

}

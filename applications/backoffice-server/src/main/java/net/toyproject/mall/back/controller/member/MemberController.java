/**
 * @author sanguk on 2023/06/11
 */

package net.toyproject.mall.back.controller.member;

import net.toyproject.mall.back.controller.common.BaseController;
import net.toyproject.mall.common.code.OrderBy;
import net.toyproject.mall.restapi.client.member.MemberApi;
import net.toyproject.mall.restapi.client.member.model.MembersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/dashboard")
public class MemberController extends BaseController {

    private static final int LIMIT_COUNT = 10;

    private static final String MEMBER_LIST_PAGE_URL = "/member-list";

    @Autowired
    private MemberApi memberApi;

    @RequestMapping(MEMBER_LIST_PAGE_URL)
    public String memberListPage(@RequestParam(name = "page", required = false) Integer page,
                                 HttpServletRequest req,
                                 Model model) {

        model.addAttribute("name", "member list");
        model.addAttribute("detail", "DataTables is a third party plugin that is used to generate the demo table below.\n" +
                "                        For more information about DataTables, please visit the <a target=\"_blank\"\n" +
                "                            href=\"https://datatables.net\">official DataTables documentation</a>.");

        model.addAttribute("seo", getDefaultSeo());

        final int offset = getOffset(page, LIMIT_COUNT);
        final MembersDTO membersDTO = memberApi.getMembers(offset, LIMIT_COUNT, OrderBy.Asc);

        model.addAttribute("pageUrl", "/dashboard" + MEMBER_LIST_PAGE_URL);
        model.addAttribute("members", membersDTO.getMembers());
        model.addAttribute("paging", setPagingParameter(membersDTO.getTotalCount(), LIMIT_COUNT, page));

        return "member/memberList";
    }


}

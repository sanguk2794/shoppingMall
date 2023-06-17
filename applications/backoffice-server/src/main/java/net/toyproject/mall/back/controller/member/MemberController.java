/**
 * @author sanguk on 2023/06/11
 */

package net.toyproject.mall.back.controller.member;

import net.toyproject.mall.back.controller.common.DashBoardController;
import net.toyproject.mall.back.util.SessionUtils;
import net.toyproject.mall.common.code.OrderBy;
import net.toyproject.mall.restapi.client.member.MemberApi;
import net.toyproject.mall.restapi.client.member.model.MembersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MemberController extends DashBoardController {

    private static final int LIMIT_COUNT = 10;

    private static final String MEMBER_LIST_PAGE_URL = "/member-list";

    @Autowired
    private MemberApi memberApi;

    @RequestMapping(MEMBER_LIST_PAGE_URL)
    public String memberListPage(@RequestParam(name = "page", required = false) Integer page,
                                 HttpServletRequest req,
                                 Model model) {

        model.addAttribute("dashboard", getDashboard("member list", "check member list"));
        model.addAttribute("seo", getDefaultSeo());

        final int offset = getOffset(page, LIMIT_COUNT);
        final MembersDTO membersDTO = memberApi.getMembers(offset, LIMIT_COUNT, OrderBy.Asc);

        model.addAttribute("pageUrl", "/dashboard" + MEMBER_LIST_PAGE_URL);
        model.addAttribute("members", membersDTO.getMembers());
        model.addAttribute("paging", setPagingParameter(membersDTO.getTotalCount(), LIMIT_COUNT, page));

        return "member/memberList";
    }
}

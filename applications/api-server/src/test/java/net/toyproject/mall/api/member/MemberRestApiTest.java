/**
 * @author sanguk on 2023/06/11
 */

package net.toyproject.mall.api.member;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import net.toyproject.mall.api.member.dto.RegisterMemberDTO;
import net.toyproject.mall.common.code.MemberPlatform;
import net.toyproject.mall.common.model.embedded.Address;
import net.toyproject.mall.common.model.embedded.Name;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MemberRestApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void beforeEach() {
        objectMapper = Jackson2ObjectMapperBuilder.json()
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .modules(new JavaTimeModule())
                .build();
    }

    @Test
    @DisplayName("会員登録テスト（成功）")
    void registerMember() throws Exception {
        final String url = "/v1/member/members";

        // Given
        RegisterMemberDTO registerMemberDTO = getSuccessRegisterMemberParam();

        // When
        ResultActions resultActions = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerMemberDTO))
        );

        // Then
        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("emailAddress").value("sanguk2794@gmail.com"));
    }

    @NotNull
    private static RegisterMemberDTO getSuccessRegisterMemberParam() {
        RegisterMemberDTO registerMemberDTO = new RegisterMemberDTO();
        registerMemberDTO.setEmailAddress("sanguk2794@gmail.com");
        final Name name = new Name();
        name.setFirstName("LEE");
        name.setLastName("SANG");
        registerMemberDTO.setName(name);
        registerMemberDTO.setPassword("1q2w3e4r");
        Address address = new Address();
        address.setZipCode("3330853");
        address.setAddress1("123123");
        address.setAddress2("123123");
        address.setAddress3("123123");
        address.setAddress4("123123");
        registerMemberDTO.setAddress(address);
        registerMemberDTO.setMemberPlatform(MemberPlatform.BackOffice);
        return registerMemberDTO;
    }

    @Test
    @DisplayName("会員登録テスト（失敗、メールアドレスなし）")
    void registerMemberNotSetEmailAddress() throws Exception {
        final String url = "/v1/member/members";

        // Given
        RegisterMemberDTO registerMemberDTO = new RegisterMemberDTO();
        final Name name = new Name();
        name.setFirstName("LEE");
        name.setLastName("SANG");
        registerMemberDTO.setName(name);
        registerMemberDTO.setPassword("1q2w3e4r");
        Address address = new Address();
        address.setZipCode("3330853");
        address.setAddress1("123123");
        address.setAddress2("123123");
        address.setAddress3("123123");
        address.setAddress4("123123");
        registerMemberDTO.setAddress(address);
        registerMemberDTO.setMemberPlatform(MemberPlatform.BackOffice);

        // When
        ResultActions resultActions = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerMemberDTO))
        );

        // Then
        resultActions.andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("会員登録テスト（失敗、パスワードなし）")
    void registerMemberNotSetPassword() throws Exception {
        final String url = "/v1/member/members";

        // Given
        RegisterMemberDTO registerMemberDTO = new RegisterMemberDTO();
        registerMemberDTO.setEmailAddress("sanguk2794@gmail.com");
        final Name name = new Name();
        name.setFirstName("LEE");
        name.setLastName("SANG");
        registerMemberDTO.setName(name);
        Address address = new Address();
        address.setZipCode("3330853");
        address.setAddress1("123123");
        address.setAddress2("123123");
        address.setAddress3("123123");
        address.setAddress4("123123");
        registerMemberDTO.setAddress(address);
        registerMemberDTO.setMemberPlatform(MemberPlatform.BackOffice);

        // When
        ResultActions resultActions = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerMemberDTO))
        );

        // Then
        resultActions.andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("会員登録テスト（失敗、プレートフォームなし）")
    void registerMemberNotSetPlatform() throws Exception {
        final String url = "/v1/member/members";

        // Given
        RegisterMemberDTO registerMemberDTO = new RegisterMemberDTO();
        registerMemberDTO.setEmailAddress("sanguk2794@gmail.com");
        final Name name = new Name();
        name.setFirstName("LEE");
        name.setLastName("SANG");
        registerMemberDTO.setName(name);
        registerMemberDTO.setPassword("1q2w3e4r");
        Address address = new Address();
        address.setZipCode("3330853");
        address.setAddress1("123123");
        address.setAddress2("123123");
        address.setAddress3("123123");
        address.setAddress4("123123");
        registerMemberDTO.setAddress(address);

        // When
        ResultActions resultActions = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerMemberDTO))
        );

        // Then
        resultActions.andExpect(status().isBadRequest());
    }
}

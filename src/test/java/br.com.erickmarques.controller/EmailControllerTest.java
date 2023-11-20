package br.com.erickmarques.controller;

import br.com.erickmarques.service.EmailService;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Transactional
public class EmailControllerTest {
    private static final String ENDPOINT_URL = "/api/email";

    @InjectMocks
    private EmailController emailController;

    @Mock
    private EmailService emailService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(emailController)
                .build();
    }

    @Test
    public void findAllByPage() throws Exception {
        Page<EmailDto> page = new PageImpl<>(Collections.singletonList(EmailBuilder.getDto()));

        Mockito.when(emailService.findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenReturn(page);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.content", Matchers.hasSize(1)));

        Mockito.verify(emailService, Mockito.times(1)).findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any());
        Mockito.verifyNoMoreInteractions(emailService);
    }

    @Test
    public void getById() throws Exception {
        Mockito.when(emailService.findById(ArgumentMatchers.anyLong()))
                .thenReturn(EmailBuilder.getDto());

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));

        Mockito.verify(emailService, Mockito.times(1)).findById("1");
        Mockito.verifyNoMoreInteractions(emailService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(emailService.save(ArgumentMatchers.any(EmailDto.class)))
                .thenReturn(EmailBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.post(ENDPOINT_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(EmailBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        Mockito.verify(emailService, Mockito.times(1)).save(ArgumentMatchers.any(EmailDto.class));
        Mockito.verifyNoMoreInteractions(emailService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(emailService.update(ArgumentMatchers.any(), ArgumentMatchers.anyLong()))
                .thenReturn(EmailBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(EmailBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(emailService, Mockito.times(1))
                .update(ArgumentMatchers.any(EmailDto.class), ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(emailService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(emailService).deleteById(ArgumentMatchers.anyLong());

        mockMvc.perform(
                        MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(EmailBuilder.getIds())))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(emailService, Mockito.times(1))
                .deleteById(Mockito.anyLong());
        Mockito.verifyNoMoreInteractions(emailService);
    }
}

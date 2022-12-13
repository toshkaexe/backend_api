package server.restfull;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import server.restfull.repo.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.containsString;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private UserRepository userRepository;

	@Test
	public void contentHomePage() throws Exception {
		this.mockMvc.perform(get("/"))//
				.andDo(print())//
				.andExpect(status().isOk()) //
				.andExpect(content().string(containsString("Hello")));

	}

//	@Test
//	public void contextLoads() throws Exception {
//		Mockito.when(userRepository.findAll())//
//				.thenReturn(Collections.emptyList());
//
//		MvcResult mvcResult = mockMvc.perform(//
//				MockMvcRequestBuilders.get("/users/")//
//						.accept(org.springframework.http.MediaType.APPLICATION_JSON))
//				.andReturn();
//		Mockito.verify(userRepository).findAll();
//		System.out.println(mvcResult.getResponse());
//
//	}

}

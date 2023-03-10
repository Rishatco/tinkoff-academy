package ru.tinkoff.landscapeService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LandscapeApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private BuildProperties buildProperties;


	@Test
	void contextLoads() {
	}

	@Test
	public void testGetLiveness() throws Exception {
		String ulr = "/system/liveness";
		this.mockMvc.perform(get(ulr)).andExpect(status().isOk());
	}


	@Test
	public void testGetReadiness() throws Exception {
		String ulr = "/system/readiness";
		ResultActions resultActions = this.mockMvc.perform(get(ulr));
		resultActions.andExpect(status().isOk());
		resultActions.andExpect(content().string(containsString("\"LandscapeService\":\"OK\"")));
	}
}

package com.prueba.reserve.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;


public class WebControllerTest {

     @Test
    public void testHandleRequestView() throws Exception{	
        WebController controller = new WebController();
        ModelAndView modelAndView = controller.handleRequest(null, null);		
        assertEquals("index.html", modelAndView.getViewName());
    }

}

package app.com.toplist.Model.DAO;

import static org.junit.Assert.*;
import android.support.test.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import app.com.toplist.DTO.responses.TopListDTO;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by Balwinder Rajput on 01-08-2017.
 * Copyright (c) 2017 M800 inc. All rights reserved.
 */
public class TopListDAOTest {
    private TopListDAO topListDAO;

    @Before
    public void setUp(){
        topListDAO = new TopListDAO(InstrumentationRegistry.getTargetContext());
    }

    @After
    public void finish() {
        topListDAO=null;
    }

    @Test
    public void testPreConditions() {
        assertNotNull(topListDAO);
    }

    @Test
    public void testgetData() {
        List<TopListDTO> listDTOs=(List<TopListDTO>)topListDAO.getData();
        assertNotNull(listDTOs);
    }
    @Test
    public void testinsertData() {
        TopListDTO topListdto=new TopListDTO();
        topListDAO.insertData(topListdto);
        List<TopListDTO> listDTOs=(List<TopListDTO>)topListDAO.getData();
        assertNotNull(listDTOs);
    }






}
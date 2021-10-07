package com.blue.project.modules.documents.services;


import com.blue.project.modules.documents.dao.MenuItemRepository;
import com.blue.project.modules.documents.dao.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    
}

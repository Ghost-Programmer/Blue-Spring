package com.blue.project.modules.documents.services;

import com.blue.project.dto.StatusMessage;
import com.blue.project.modules.documents.dto.PageDto;
import com.blue.project.modules.documents.dto.PageSearch;
import com.blue.project.modules.documents.models.Page;

import java.util.List;

public interface PageService {
    Page findByUuid(String uuid);

    Page updatePage(Page page);

    Page createPage(Page page);

    StatusMessage deletePage(Long id);

    PageSearch search(PageSearch pageSearch);

    List<PageDto> getAllPages();
}
